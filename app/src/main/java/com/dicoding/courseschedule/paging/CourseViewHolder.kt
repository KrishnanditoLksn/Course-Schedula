package com.dicoding.courseschedule.paging

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.util.DayName.Companion.getByNumber

class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var course: Course
    private val timeString = itemView.context.resources.getString(R.string.time_format)
    private val tvTittle = itemView.findViewById<TextView>(R.id.tv_course)
    private val tvTime = itemView.findViewById<TextView>(R.id.tv_time)
    private val tvLecturer = itemView.findViewById<TextView>(R.id.tv_lecturer)

    //TODO 7 : Complete ViewHolder to show item
    fun bind(course: Course, clickListener: (Course) -> Unit) {
        this.course = course

        course.apply {
            val dayName = getByNumber(day)
            val timeFormat = String.format(timeString, dayName, startTime, endTime)
            tvTittle.text = course.courseName
            tvTime.text = timeFormat
            tvLecturer.text = course.lecturer
        }

        itemView.setOnClickListener {
            clickListener(course)
        }
    }

    fun getCourse(): Course = course
}