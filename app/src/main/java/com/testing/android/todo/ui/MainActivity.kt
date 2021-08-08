package com.testing.android.todo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.google.android.material.composethemeadapter.MdcTheme
import com.testing.android.todo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            val modifier = Modifier
            MdcTheme {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Greeting()
                }
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