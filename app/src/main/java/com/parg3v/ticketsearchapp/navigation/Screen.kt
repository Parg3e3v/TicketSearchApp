package com.parg3v.ticketsearchapp.navigation

sealed class Screen(val route: String) {
    data object AirlineTicketsScreen : Screen("airline_tickets_screen")

    data object ToDoScreen : Screen("to_do_screen")

    data object SpecificSearchScreen : Screen("specific_search_screen")

    data object TicketsScreen: Screen("tickets_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}