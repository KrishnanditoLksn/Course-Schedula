package com.dicoding.courseschedule.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteQuery

//TODO 2 : Define data access object (DAO)
@Dao
interface CourseDao {

    fun getNearestSchedule(query: SupportSQLiteQuery): LiveData<Course?>

    fun getAll(query: SupportSQLiteQuery): PagingSource<Int, Course>

    @Query("SELECT * FROM course")
    fun getCourse(id: Int): LiveData<Course>

    fun getTodaySchedule(day: Int): List<Course>

    @Insert
    fun insert(course: Course)

    @Delete
    fun delete(course: Course)
}