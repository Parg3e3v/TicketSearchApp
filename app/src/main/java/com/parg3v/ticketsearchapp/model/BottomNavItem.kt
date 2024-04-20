package com.parg3v.ticketsearchapp.model

import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.navigation.Screen

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Int
) {
    object Home :
        BottomNavItem("", Screen.HomeScreen.route, R.drawable.ic_launcher_background)
}