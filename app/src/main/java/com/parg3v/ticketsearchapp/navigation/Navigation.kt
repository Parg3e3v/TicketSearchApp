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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsScreen
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsViewModel
import com.parg3v.ticketsearchapp.view.specificSearch.SpecificSearchScreen
import com.parg3v.ticketsearchapp.view.specificSearch.SpecificSearchViewModel
import com.parg3v.ticketsearchapp.view.tickets.TicketsScreen
import com.parg3v.ticketsearchapp.view.tickets.TicketsViewModel
import com.parg3v.ticketsearchapp.view.todoScreens.ToDoScreen

@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    showBottomSheet: MutableState<Boolean>,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit,
    fromFieldStateProvider: () -> String?,
    fromFieldInputChange: (String) -> Unit
) {

    val airlineTicketsViewModel: AirlineTicketsViewModel = hiltViewModel()
    val specificSearchViewModel: SpecificSearchViewModel = hiltViewModel()
    val ticketsViewModel: TicketsViewModel = hiltViewModel()

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

            AirlineTicketsScreen(
                offersState = offersState,
                fromFieldStateProvider = fromFieldStateProvider,
                fromFieldInputChange = fromFieldInputChange,
                toFieldStateProvider = toFieldStateProvider,
                toFieldInputChange = toFieldInputChange,
                showBottomSheet = showBottomSheet
            )
        }
        composable(
            route = Screen.SpecificSearchScreen.route,
            popEnterTransition = { slideIn },
            exitTransition = { slideOut }
        ) {
            val ticketOffersState by specificSearchViewModel.ticketOffersState.collectAsStateWithLifecycle()

            LaunchedEffect(specificSearchViewModel) {
                specificSearchViewModel.getAllTicketOffers()
            }

            SpecificSearchScreen(
                navController = navController,
                fromFieldStateProvider = fromFieldStateProvider,
                fromFieldInputChange = fromFieldInputChange,
                toFieldStateProvider = toFieldStateProvider,
                toFieldInputChange = toFieldInputChange,
                ticketOffersState = ticketOffersState
            )
        }
        composable(
            route = "${Screen.TicketsScreen.route}/{date}/{passengers}/{from}/{to}",
            arguments = listOf(
                navArgument("date") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("passengers") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("from") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("to") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
            ),
            popEnterTransition = { slideIn },
            exitTransition = { slideOut }
        ) { entry ->
            val date = entry.arguments?.getString("date") ?: ""
            val passengers = entry.arguments?.getString("passengers") ?: ""
            val from = entry.arguments?.getString("from") ?: ""
            val to = entry.arguments?.getString("to") ?: ""
            val ticketsState by ticketsViewModel.ticketsState.collectAsStateWithLifecycle()

            LaunchedEffect(ticketsViewModel) {
                ticketsViewModel.getAllTickets()
            }

            TicketsScreen(
                navController = navController,
                ticketsState = ticketsState,
                dateProvider = { date },
                passengersProvider = { passengers },
                fromProvider = { from },
                toProvider = { to }
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