package com.parg3v.ticketsearchapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsScreen
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsViewModel
import com.parg3v.ticketsearchapp.view.todoScreen.ToDoScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    showBottomSheet: MutableState<Boolean>,
    airlineTicketsViewModel: AirlineTicketsViewModel,
    toFieldStateProvider: () -> String,
    fromFieldStateProvider: () -> String?
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
            val offersState by airlineTicketsViewModel.offersState.collectAsStateWithLifecycle()
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                airlineTicketsViewModel.getFromFieldValue(context = context)
            }

            AirlineTicketsScreen(
                offersStateProvider = { offersState },
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