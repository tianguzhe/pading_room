package com.rongyi.pagingdemo.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
class Student(
    @ColumnInfo(name = "student_number") var studentNumber: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}