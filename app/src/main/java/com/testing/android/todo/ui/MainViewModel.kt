package com.testing.android.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val todoRepo: TodoRepo) : ViewModel() {

    private val _todos = MutableLiveData<Resource<List<Todo>>>()
    val todos: LiveData<Resource<List<Todo>>> = _todos

    fun getTodos() {
        viewModelScope.launch {
            _todos.value = Resource.Loading(null, null)
            try {
                val result = todoRepo.getAllTodos()
                _todos.value = Resource.Success(result, null)
            } catch (e: Exception) {
                println("Error $e")
                _todos.value = Resource.Error(null, "Something went wrong")
            }
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                todoRepo.updateTodo(todo)
                val result = todoRepo.getAllTodos()
                _todos.value = Resource.Success(result, null)
            } catch (e: Exception) {
                println("Error $e")
                _todos.value = Resource.Error(null, "Something went wrong")
            }
        }
    }
}