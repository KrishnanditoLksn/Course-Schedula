package com.dicoding.courseschedule.data

import androidx.room.Entity

//TODO 1 : Define a local database table using the schema in app/schema/course.json
@Entity(tableName = "course")
data class Course(
    val id: Int = 0,
    val courseName: String,
    val day: Int,
    val startTime: String,
    val endTime: String,
    val lecturer: String,
    val note: String
)
