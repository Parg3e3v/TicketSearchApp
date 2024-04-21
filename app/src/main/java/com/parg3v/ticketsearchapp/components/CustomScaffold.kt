package com.parg3v.ticketsearchapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.parg3v.ticketsearchapp.model.BottomNavItem

@Composable
fun CustomScaffold(
    navController: NavController,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.AirlineTickets,
        BottomNavItem.Hotels,
        BottomNavItem.InShort,
        BottomNavItem.Subscriptions,
        BottomNavItem.Profile
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {

    Box {
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                    content = {
                        BottomNavigationBar(
                            items = items,
                            navController = navController,
                            modifier = Modifier,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    })
            }
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}