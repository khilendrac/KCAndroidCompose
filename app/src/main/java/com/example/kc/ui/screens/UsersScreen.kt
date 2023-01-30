package com.example.kc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kc.viewmodels.UsersViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UsersScreen(viewModel: UsersViewModel) {

    val users = viewModel.users.observeAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 20.dp, start = 20.dp)
    ) {
        Text("User Details", color = Color.Red, style = TextStyle(fontSize = 20.sp))
        users.value?.data?.forEach {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                Text("Name: ${it.firstName} ${it.lastName}", color = Color.White)
                Text("Email: ${it.email}", color = Color.White)
            }
        }
    }
}
