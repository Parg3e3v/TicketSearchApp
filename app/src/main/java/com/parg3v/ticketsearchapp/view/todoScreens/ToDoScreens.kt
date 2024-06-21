package com.parg3v.ticketsearchapp.view.todoScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun ToDoScreen(text: String, navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = Modifier.fillMaxSize().clickable(interactionSource = interactionSource, indication = null) { navController.popBackStack() }) {
        Text(text = "$text\n(click)", modifier = Modifier.align(Alignment.Center), textAlign = TextAlign.Center)
    }
}