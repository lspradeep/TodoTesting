package com.testing.android.todo.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.data.localdb.TodoDB
import com.testing.android.todo.data.localdb.TodoDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class TodoDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_todo_db")
    lateinit var database: TodoDB

    private lateinit var todoDao: TodoDao

    @Before
    fun setup() {
        hiltRule.inject()
        todoDao = database.todoDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun insertUser() = runBlockingTest {
        val todo = Todo(1, "tile", "asasc", false)
        todoDao.addTodo(todo)
        val allTodos = todoDao.getAllTodos()
        assertThat(allTodos).contains(todo)
        assertThat(allTodos.size).isEqualTo(1)
    }

}