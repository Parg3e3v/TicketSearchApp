package com.parg3v.ticketsearchapp.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsViewModel
import com.parg3v.ticketsearchapp.view.airlineTickets.HomeScreen
import com.parg3v.ticketsearchapp.view.todoScreen.ToDoScreen

@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    val airlineTicketsViewModel: AirlineTicketsViewModel = hiltViewModel()

    val slideIn = slideInHorizontally(
        initialOffsetX = { -300 }, animationSpec = tween(
            durationMillis = 300, easing = FastOutSlowInEasing
        )
    ) + fadeIn(animationSpec = tween(300))

    val slideOut = slideOutHorizontally(
        targetOffsetX = { -300 }, animationSpec = tween(
            durationMillis = 300, easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(300))

    NavHost(
        navController = navController,
        startDestination = Screen.AirlineTicketsScreen.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(
            route = Screen.AirlineTicketsScreen.route,
            popEnterTransition = { slideIn },
            exitTransition = { slideOut }
        ){
            val offersState by airlineTicketsViewModel.offersState.collectAsStateWithLifecycle()
            HomeScreen(navController = navController, offersState = offersState)
        }
        composable(
            route = Screen.HotelsScreen.route,
            popEnterTransition = { slideIn },
            exitTransition = { slideOut }
        ){
            ToDoScreen(text = Screen.HotelsScreen.route)
        }
        composable(
            route = Screen.InShortScreen.route,
            popEnterTransition = { slideIn },
            exitTransition = { slideOut }
        ){
            ToDoScreen(text = Screen.InShortScreen.route)
        }
        composable(
            route = Screen.SubscriptionsScreen.route,
            popEnterTransition = { slideIn },
            exitTransition = { slideOut }
        ){
            ToDoScreen(text = Screen.SubscriptionsScreen.route)
        }
        composable(
            route = Screen.ProfileScreen.route,
            popEnterTransition = { slideIn },
            exitTransition = { slideOut }
        ){
            ToDoScreen(text = Screen.ProfileScreen.route)
        }
    }
}