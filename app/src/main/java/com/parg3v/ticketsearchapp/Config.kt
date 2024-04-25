package com.parg3v.ticketsearchapp

import android.icu.text.NumberFormat
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale
import kotlin.math.round

fun calculateFlightDuration(departure: Date, arrival: Date): String {
    val durationInMillis = arrival.time - departure.time
    val durationInHours = durationInMillis.toDouble() / (1000 * 60 * 60)
    val rounded = (round(durationInHours * 2) / 2) // Round to the nearest half-hour
    return if (rounded % 1 == 0.0) {
        rounded.toInt().toString()
    } else {
        rounded.toString()
    }
}


fun formatWithThousandSeparator(value: Int): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
    val formattedString = numberFormat.format(value)
    return formattedString.replace(",", " ")
}

fun navigateAndClose(
    scope: CoroutineScope,
    navController: NavController,
    closeBottomSheet: suspend () -> Unit,
    route: String
) {
    navController.navigate(route)
    scope.launch {
        closeBottomSheet()
    }
}