package com.testing.android.todo.repo

import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.data.localdb.TodoDao
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(private val todoDao: TodoDao) : TodoRepo {
    override suspend fun getAllTodos(): List<Todo> {
        return todoDao.getAllTodos()
    }

    override suspend fun getCompletedTodos(): List<Todo> {
        return todoDao.getCompletedTodos()
    }

    override suspend fun getIncompleteTodos(): List<Todo> {
        return todoDao.getIncompleteTodos()
    }

    override suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}