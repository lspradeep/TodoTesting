package com.testing.android.todo.repo

import com.testing.android.todo.data.localdb.Todo

interface TodoRepo {
    suspend fun getAllTodos(): List<Todo>

    suspend fun getCompletedTodos(): List<Todo>

    suspend fun getIncompleteTodos(): List<Todo>

    suspend fun addTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}