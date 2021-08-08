package com.testing.android.todo.ui.addtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor() : ViewModel() {
    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    // onNameChange is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onTitleChange(newName: String) {
        _title.value = newName
    }


}