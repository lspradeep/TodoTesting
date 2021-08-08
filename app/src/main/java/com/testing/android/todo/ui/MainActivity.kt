package com.testing.android.todo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import com.testing.android.todo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<ComposeView>(R.id.compose_view)?.setContent {
            Text(text = "hello")
        }
    }
}