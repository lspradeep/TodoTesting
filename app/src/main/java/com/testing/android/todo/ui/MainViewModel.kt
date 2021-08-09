package com.testing.android.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val todoRepo: TodoRepo) : ViewModel() {

    private val _todos = MutableLiveData<Resource<List<Todo>>>()
    val todos: LiveData<Resource<List<Todo>>> = _todos

    private val _update = MutableLiveData<Resource<String?>>()
    val update: LiveData<Resource<String?>> = _update

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
            _update.value = Resource.Loading(null, null)
            try {
                todoRepo.updateTodo(todo)
                _update.value = Resource.Success(null, null)
            } catch (e: Exception) {
                println("Error $e")
                _update.value = Resource.Error(null, "Something went wrong")
            }
        }
    }
}