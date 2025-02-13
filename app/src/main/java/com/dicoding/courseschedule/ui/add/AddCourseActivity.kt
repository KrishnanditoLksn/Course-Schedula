package com.dicoding.courseschedule.ui.add

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.list.ListActivity
import com.dicoding.courseschedule.util.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {
    private lateinit var viewModel: AddCourseViewModel
    private var startTime = ""
    private var endTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        supportActionBar?.title = resources.getString(R.string.add_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = AddCourseViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, ListActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }

            R.id.action_insert -> {
                insertCourse()
                true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun insertCourse() {
        val courseName = findViewById<EditText>(R.id.ed_course_name).text.toString()
        val day = findViewById<Spinner>(R.id.spinner_day).selectedItemPosition
        val lecturer = findViewById<EditText>(R.id.ed_lecturer).text.toString()
        val note = findViewById<EditText>(R.id.ed_note).text.toString()

        viewModel.insertCourse(
            courseName = courseName,
            day = day,
            startTime = startTime,
            endTime = endTime,
            lecturer = lecturer,
            note = note
        )

        val intent = Intent(this@AddCourseActivity, ListActivity::class.java)
        startActivity(intent)
    }

    fun showTimePicker(view: View) {
        val tag = when (view.id) {
            R.id.ib_start_time -> {
                "startTime"
            }

            R.id.ib_end_time -> {
                "endTime"
            }

            else -> {
                ""
            }
        }
        val timePickerFragment = TimePickerFragment()
        timePickerFragment.show(supportFragmentManager, tag)
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        val time = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag) {
            "startTime" -> {
                findViewById<TextView>(R.id.tv_start_time).text = time.format(calendar.time)
                startTime = time.format(calendar.time)
            }

            "endTime" -> {
                findViewById<TextView>(R.id.tv_end_time).text = time.format(calendar.time)
                endTime = time.format(calendar.time)
            }
        }
    }


}