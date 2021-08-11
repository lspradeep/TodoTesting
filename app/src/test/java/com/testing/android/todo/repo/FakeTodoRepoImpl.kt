package com.testing.android.todo.repo

import com.testing.android.todo.data.localdb.Todo

class FakeTodoRepoImpl : TodoRepo {
    private val todos = mutableListOf<Todo>()

    override suspend fun getAllTodos(): List<Todo> {
        return todos
    }

    override suspend fun getCompletedTodos(): List<Todo> {
        return todos
    }

    override suspend fun getIncompleteTodos(): List<Todo> {
        return todos
    }

    override suspend fun addTodo(todo: Todo) {
        todos.add(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        val updateIndex = todos.indexOf(todo)
        todos[updateIndex] = todo
    }

    override suspend fun deleteTodo(todo: Todo) {
        todos.remove(todo)
    }
}