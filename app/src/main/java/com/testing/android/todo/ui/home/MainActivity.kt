package com.testing.android.todo.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.testing.android.todo.R
import com.testing.android.todo.data.localdb.Todo
import com.testing.android.todo.ui.addtodo.AddTodoActivity
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
            val rememberListState = rememberLazyListState()
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
                            LazyColumn(
                                state = rememberListState,
                                contentPadding = PaddingValues(bottom = 80.dp),
                                modifier = modifier
                                    .fillMaxWidth()
                                    .align(alignment = Alignment.TopStart)) {
                                items(todos.value?.data.orEmpty()) { todo ->
                                    TodoItem(modifier = modifier,
                                        todo = todo,
                                        onClick = { check ->
                                            mainViewModel.updateTodo(todo.copy(completed = check))
                                        })
                                    Divider()

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
        Row(modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                mainViewModel.updateTodo(todo.copy(completed = !todo.completed))
            },
            verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = modifier.weight(1f)) {
                Text(text = todo.title)
                Spacer(modifier = modifier.height(4.dp))
                Text(text = todo.content)
            }
            Spacer(modifier = modifier.height(8.dp))
            Checkbox(checked = todo.completed, onCheckedChange = onClick)
        }
    }

}