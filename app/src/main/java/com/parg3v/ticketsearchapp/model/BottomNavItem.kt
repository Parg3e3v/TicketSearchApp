package com.parg3v.ticketsearchapp.model

import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.navigation.Screen

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Int
) {
    object AirlineTickets : BottomNavItem("Авиабилеты", Screen.AirlineTicketsScreen.route, R.drawable.airline_tickets_icon)
    object Hotels : BottomNavItem("Отели", Screen.HotelsScreen.route, R.drawable.hotels_icon)
    object InShort : BottomNavItem("Короче", Screen.InShortScreen.route, R.drawable.in_short_icon)
    object Subscriptions : BottomNavItem("Подписки", Screen.SubscriptionsScreen.route, R.drawable.subscriptions_icon)
    object Profile : BottomNavItem("Профиль", Screen.ProfileScreen.route, R.drawable.profile_icon)
}