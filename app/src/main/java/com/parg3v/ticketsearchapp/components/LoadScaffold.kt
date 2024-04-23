package com.parg3v.ticketsearchapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.parg3v.ticketsearchapp.navigation.Navigation
import com.parg3v.ticketsearchapp.view.airlineTickets.AirlineTicketsViewModel

@Composable
fun LoadScaffold(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showBottomSheet: MutableState<Boolean>
) {

    val airlineTicketsViewModel: AirlineTicketsViewModel = hiltViewModel()

    val fromFieldValue by airlineTicketsViewModel.fromFieldState.collectAsStateWithLifecycle()
    val toFieldValue by airlineTicketsViewModel.toFieldState.collectAsStateWithLifecycle()


    CustomScaffold(
        modifier = modifier,
        navController = navController,
        fromFieldStateProvider = { fromFieldValue.data },
        fromFieldInputChange = airlineTicketsViewModel::validateFromField,
        toFieldStateProvider = { toFieldValue },
        toFieldInputChange = airlineTicketsViewModel::validateToField,
        showBottomSheet = showBottomSheet
    ) { paddingValues ->
        Navigation(
            navController = navController,
            paddingValues = paddingValues,
            showBottomSheet = showBottomSheet,
            airlineTicketsViewModel = airlineTicketsViewModel,
            toFieldStateProvider = { toFieldValue },
            fromFieldStateProvider = { fromFieldValue.data }
        )
    }
}