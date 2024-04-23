package com.parg3v.ticketsearchapp.view.todoScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ToDoScreen(text: String, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().clickable { navController.popBackStack() }) {
        Text(text = text, modifier = Modifier.align(Alignment.Center))
    }
}