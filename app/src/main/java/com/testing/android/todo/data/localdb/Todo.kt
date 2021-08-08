package com.testing.android.todo.data.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "completed") val completed: Boolean
)