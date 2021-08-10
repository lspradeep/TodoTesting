package com.testing.android.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepo: TodoRepo) : ViewModel() {

    private val _todos = MutableLiveData<Resource<List<Todo>>>()
    val todos: LiveData<Resource<List<Todo>>> = _todos

    fun getTodos() {
        viewModelScope.launch {
            _todos.value = Resource.Loading(null, null)
            try {
                val result = withContext(Dispatchers.IO) {
                    todoRepo.getAllTodos()
                }
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
                val result = withContext(Dispatchers.IO) {
                    todoRepo.updateTodo(todo)
                    todoRepo.getAllTodos()
                }
                _todos.value = Resource.Success(result, null)
            } catch (e: Exception) {
                println("Error $e")
                _todos.value = Resource.Error(null, "Something went wrong")
            }
        }
    }

    //Add or Update Todo


    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    fun onTitleChange(newName: String) {
        _title.value = newName
    }


    private val _detail = MutableLiveData("")
    val detail: LiveData<String> = _detail

    fun onContentChange(newName: String) {
        _detail.value = newName
    }

    private val _addTodoStatus = MutableLiveData<Resource<String?>>()
    val addTodoStatus: LiveData<Resource<String?>> = _addTodoStatus

    fun addTodo() {
        if (title.value.isNullOrBlank()) {
            _addTodoStatus.value = Resource.Error(null, "Please provide title")
            return
        }
        if (detail.value.isNullOrBlank()) {
            _addTodoStatus.value = Resource.Error(null, "Please provide content")
            return
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    todoRepo.addTodo(Todo(title = title.value ?: "",
                        content = detail.value ?: "",
                        completed = false))
                }
                _addTodoStatus.value = Resource.Success(null, null)
            } catch (e: Exception) {
                println("Error $e")
                _addTodoStatus.value = Resource.Error(null, "Something went wrong")
            }
        }
    }
}