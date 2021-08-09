package com.testing.android.todo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.testing.android.todo.R
import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.ui.addtodo.AddTodoActivity
import com.testing.android.todo.ui.addtodo.AddTodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        getTodos()
    }

    private fun getTodos() {
        mainViewModel.getTodos()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val todos = mainViewModel.todos.observeAsState()
            val update = mainViewModel.update.observeAsState()
            if (update.value?.status == Resource.Status.SUCCESS) {
                getTodos()
            } else if (update.value?.status == Resource.Status.ERROR) {
                Toast.makeText(this, update.value?.message, Toast.LENGTH_SHORT).show()
            }
            val modifier = Modifier
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar {
                            Text(text = getString(R.string.app_name),
                                modifier = modifier.padding(8.dp))
                        }
                    },
                    content = {
                        Box(modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            LazyColumn {
                                item {
                                    todos.value?.data?.forEach {
                                        val checked = remember {
                                            mutableStateOf(false)
                                        }
                                        TodoItem(modifier = modifier,
                                            todo = it,
                                            onClick = { check ->
                                                mainViewModel.updateTodo(it.copy(completed = check))
                                            })
                                    }
                                }
                            }

                            FloatingActionButton(
                                modifier = modifier
                                    .align(alignment = Alignment.BottomEnd)
                                    .padding(end = 16.dp, bottom = 16.dp),
                                onClick = {
                                    startActivity(
                                        Intent(
                                            this@MainActivity,
                                            AddTodoActivity::class.java
                                        )
                                    )
                                }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "")
                            }
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun TodoItem(
        modifier: Modifier,
        todo: Todo,
        onClick: (Boolean) -> Unit,
    ) {
        Row(modifier = modifier.padding(4.dp)) {
            Column {
                Text(text = todo.title)
                Spacer(modifier = modifier.height(4.dp))
                Text(text = todo.content)
            }
            Checkbox(checked = todo.completed, onCheckedChange = onClick)
        }
    }

}