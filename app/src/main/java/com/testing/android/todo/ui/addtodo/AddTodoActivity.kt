package com.testing.android.todo.ui.addtodo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.testing.android.todo.grey
import com.testing.android.todo.greyLight
import com.testing.android.todo.ui.Resource
import com.testing.android.todo.ui.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoActivity : AppCompatActivity() {

    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val title = viewModel.title.observeAsState()
            val detail = viewModel.detail.observeAsState()
            val addTodoStatus = viewModel.addTodoStatus.observeAsState()
            if (addTodoStatus.value?.status == Resource.Status.SUCCESS) {
                finish()
            } else if (addTodoStatus.value?.status == Resource.Status.ERROR) {
                Toast.makeText(this, addTodoStatus.value?.message, Toast.LENGTH_SHORT).show()
            }

            val modifier = Modifier
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar() {
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                modifier = modifier
                                    .padding(start = 8.dp)
                                    .clickable {
                                        finish()
                                    })

                            Text(text = "Add Todo", modifier = modifier.padding(start = 8.dp))
                        }
                    },
                    content = {
                        LazyColumn(modifier = modifier.padding(16.dp)) {
                            item {
                                OutlinedTextField(
                                    value = title.value ?: "",
                                    onValueChange = { value ->
                                        viewModel.onTitleChange(value)
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

                                Spacer(modifier = modifier.height(16.dp))

                                OutlinedTextField(
                                    value = detail.value ?: "",
                                    onValueChange = { value ->
                                        viewModel.onContentChange(value)
                                    },
                                    placeholder = { Text(text = "Todo Detail") },
                                    maxLines = 4,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .background(color = greyLight,
                                            shape = RoundedCornerShape(8.dp))
                                        .border(width = 1.dp,
                                            color = grey,
                                            shape = RoundedCornerShape(8.dp))
                                        .clip(RoundedCornerShape(8.dp)),
                                )

                                Spacer(modifier = modifier.height(16.dp))

                                Button(onClick = {
                                    viewModel.addTodo()
                                }, modifier = modifier.fillMaxWidth()) {
                                    Text(text = "Create Todo")
                                }
                            }
                        }
                    }
                )
            }
        }
    }

}