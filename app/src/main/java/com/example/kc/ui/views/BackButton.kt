package com.example.kc.ui.views

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun BackButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "BACK")
    }
}
