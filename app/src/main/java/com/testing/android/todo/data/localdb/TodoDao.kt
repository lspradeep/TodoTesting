package com.testing.android.todo.data.localdb

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    suspend fun getAllTodos(): List<Todo>

    @Query("SELECT * FROM todo_table WHERE completed")
    suspend fun getCompletedTodos(): List<Todo>

    @Query("SELECT * FROM todo_table WHERE NOT completed")
    suspend fun getIncompleteTodos(): List<Todo>

    @Insert
    suspend fun addTodo(todo: Todo)

    @Update()
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}