package com.parg3v.ticketsearchapp.navigation

sealed class Screen(val route: String) {
    object AirlineTicketsScreen : Screen("airline_tickets_screen")
    object HotelsScreen : Screen("hotels_screen")
    object InShortScreen : Screen("inShort_screen")
    object SubscriptionsScreen : Screen("subscriptions_screen")
    object ProfileScreen : Screen("profile_screen")
}