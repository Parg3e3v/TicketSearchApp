package com.parg3v.ticketsearchapp.navigation

sealed class Screen(val route: String, val title: String? = null) {
    object HomeScreen : Screen("home_screen", "Главная")

    companion object {
        fun getTitleByRoute(route: String?): String? {
            val matchingScreen = Screen::class.sealedSubclasses.map { it.objectInstance as Screen }
                .find { it.route == route }
            return matchingScreen?.title
        }
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}