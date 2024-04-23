package com.parg3v.ticketsearchapp.navigation

sealed class Screen(val route: String) {
    object AirlineTicketsScreen : Screen("airline_tickets_screen")

    object ToDoScreen: Screen("to_do_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}