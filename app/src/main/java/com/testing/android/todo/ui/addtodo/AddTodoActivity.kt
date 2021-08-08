package com.testing.android.todo.ui.addtodo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.material.composethemeadapter.MdcTheme
import com.testing.android.todo.R
import com.testing.android.todo.grey
import com.testing.android.todo.greyLight

class AddTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val addTodoViewModel: AddTodoViewModel = viewModel()
            val title = addTodoViewModel.title.observeAsState()

            val modifier = Modifier
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar {
                            Text(text = "Add Todo")
                        }
                    },
                    content = {
                        LazyColumn(modifier = modifier.padding(8.dp)) {
                            item {
                                OutlinedTextField(
                                    value = title.value ?: "",
                                    onValueChange = { value ->
                                        addTodoViewModel.onTitleChange(value)
                                    },
                                    placeholder = { Text(text = "Todo Title") },
                                    maxLines = 1,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .background(color = greyLight,
                                            shape = RoundedCornerShape(8.dp))
                                        .border(width = 1.dp,
                                            color = grey,
                                            shape = RoundedCornerShape(8.dp))
                                        .clip(RoundedCornerShape(8.dp)),
                                )
                            }
                        }
                    }
                )
            }
        }
    }

}