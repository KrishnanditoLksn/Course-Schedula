package com.dicoding.courseschedule.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.util.DayName.Companion.getByNumber

class DetailActivity : AppCompatActivity() {

    companion object {
        const val COURSE_ID = "courseId"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val courseId = intent.getIntExtra(COURSE_ID, 0)
        val factory = DetailViewModelFactory.createFactory(this, courseId)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        viewModel.course.observe(this) {
            showCourseDetail(it)
        }
    }

    private fun showCourseDetail(course: Course?) {
        course?.apply {
            val timeString = getString(R.string.time_format)
            val dayName = getByNumber(day)
            val timeFormat = String.format(timeString, dayName, startTime, endTime)
            val courseName = findViewById<TextView>(R.id.tv_course_name)
            val tvTime = findViewById<TextView>(R.id.tv_time)
            val tvLecturer = findViewById<TextView>(R.id.tv_lecturer)
            val tvNote = findViewById<TextView>(R.id.tv_note)

            courseName.text = course.courseName
            tvTime.text = timeFormat
            tvLecturer.text = course.lecturer
            tvNote.text = course.note
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                AlertDialog.Builder(this).apply {
                    setMessage(getString(R.string.delete_alert))
                    setNegativeButton(getString(R.string.no), null)
                    setPositiveButton(getString(R.string.yes)) { _, _ ->
                        viewModel.delete()
                        finish()
                    }
                    show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}