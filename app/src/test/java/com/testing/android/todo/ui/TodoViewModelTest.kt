package com.testing.android.todo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.testing.android.todo.MainCoroutineRule
import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.getOrAwaitValue
import com.testing.android.todo.repo.FakeTodoRepoImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TodoViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: TodoViewModel

    @Before
    fun setup() {
        mainViewModel = TodoViewModel(FakeTodoRepoImpl())
    }

    @Test
    fun `add todo with empty title returns error`() {
          mainViewModel.onTitleChange("")
          mainViewModel.onContentChange("content")
          mainViewModel.addTodo()
          val result = mainViewModel.addTodoStatus.getOrAwaitValue()
          assertThat(result.status).isEqualTo(Resource.Status.ERROR)
    }

    @Test
    fun `add todo with empty content returns error`() {
           mainViewModel.onTitleChange("title")
           mainViewModel.onContentChange("")
           mainViewModel.addTodo()
           val result = mainViewModel.addTodoStatus.getOrAwaitValue()
           assertThat(result.status).isEqualTo(Resource.Status.ERROR)
    }

    @Test
    fun `add todo returns success`() {
            mainViewModel.onTitleChange("title")
            mainViewModel.onContentChange("content")
            mainViewModel.addTodo()
            val result = mainViewModel.addTodoStatus.getOrAwaitValue()
            assertThat(result.status).isEqualTo(Resource.Status.SUCCESS)
    }

    @Test
    fun `add one todo return size one`() {
            mainViewModel.onTitleChange("title")
            mainViewModel.onContentChange("content")
            mainViewModel.addTodo()
            mainViewModel.getTodos()
            val result = mainViewModel.todos.getOrAwaitValue()
            println("res ${result.data?.size}")
            assertThat(result.status).isEqualTo(Resource.Status.SUCCESS)
            assertThat(result.data?.size).isEqualTo(1)
    }

    @Test
    fun `add two todo return size two`() {
            mainViewModel.onTitleChange("title")
            mainViewModel.onContentChange("content")
            mainViewModel.addTodo()
            mainViewModel.onTitleChange("title")
            mainViewModel.onContentChange("content")
            mainViewModel.addTodo()

            mainViewModel.getTodos()
            val result = mainViewModel.todos.getOrAwaitValue()
            println("res ${result.data?.size}")
            assertThat(result.status).isEqualTo(Resource.Status.SUCCESS)
            assertThat(result.data?.size).isEqualTo(2)
    }
}
