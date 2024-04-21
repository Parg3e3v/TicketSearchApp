package com.parg3v.ticketsearchapp.view.airlineTickets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.parg3v.ticketsearchapp.model.OffersState

@Composable
fun HomeScreen(navController: NavController, offersState: OffersState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello Home")
            offersState.data?.let {
                for (i in it) {
                    Text(text = i.title)
                }
            }

        }
    }
}