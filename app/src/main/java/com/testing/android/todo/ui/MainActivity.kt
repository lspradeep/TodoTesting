package com.testing.android.todo.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.testing.android.todo.R
import com.testing.android.todo.ui.addtodo.AddTodoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                            Text(text = "Hello")

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
    private fun Greeting() {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}