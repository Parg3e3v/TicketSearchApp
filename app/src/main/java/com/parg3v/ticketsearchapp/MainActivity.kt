package com.parg3v.ticketsearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.parg3v.ticketsearchapp.components.LoadScaffold
import com.parg3v.ticketsearchapp.ui.theme.TicketSearchAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketSearchAppTheme {
                val showBottomSheet = rememberSaveable { mutableStateOf(false) }
                val navHostController: NavHostController = rememberNavController()

                LoadScaffold(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_screen_horizontal)),
                    navController = navHostController, showBottomSheet = showBottomSheet
                )

            }
        }
    }
}