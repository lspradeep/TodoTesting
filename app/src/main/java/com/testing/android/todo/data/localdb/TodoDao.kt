package com.testing.android.todo.data.localdb

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): List<Todo>

    @Query("SELECT * FROM todo_table WHERE completed")
    fun getCompletedTodos(): List<Todo>

    @Query("SELECT * FROM todo_table WHERE NOT completed")
    fun getIncompleteTodos(): List<Todo>
}