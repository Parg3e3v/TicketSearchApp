package com.parg3v.ticketsearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.parg3v.ticketsearchapp.components.CustomScaffold
import com.parg3v.ticketsearchapp.navigation.Navigation
import com.parg3v.ticketsearchapp.ui.theme.TicketSearchAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketSearchAppTheme {
                val navHostController: NavHostController = rememberNavController()
                CustomScaffold(navController = navHostController) { paddingValues ->
                    Navigation(navController = navHostController, paddingValues = paddingValues)
                }
            }
        }
    }
}