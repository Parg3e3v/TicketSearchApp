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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.parg3v.ticketsearchapp.model.OffersState
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsScreen
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsViewModel
import com.parg3v.ticketsearchapp.view.todoScreen.ToDoScreen

@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    showBottomSheet: MutableState<Boolean>,
    airlineTicketsViewModel: AirlineTicketsViewModel,
    toFieldStateProvider: () -> String,
    fromFieldStateProvider: () -> String?,
    offersStateProvider: () -> OffersState
) {


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
        ) {
            AirlineTicketsScreen(
                offersStateProvider = offersStateProvider,
                fromFieldStateProvider = fromFieldStateProvider,
                fromFieldInputChange = airlineTicketsViewModel::validateFromField,
                toFieldStateProvider = toFieldStateProvider,
                toFieldInputChange = airlineTicketsViewModel::validateToField,
                showBottomSheet = showBottomSheet
            )
        }
        composable(route = "${Screen.ToDoScreen.route}/{title}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
                defaultValue = "To Do"
                nullable = false
            }),
            exitTransition = { slideOut },
            popEnterTransition = { slideIn }) { entry ->
            val title = entry.arguments?.getString("title")
            ToDoScreen(text = title ?: "To Do", navController = navController)
        }
    }
}