package com.parg3v.ticketsearchapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.parg3v.ticketsearchapp.navigation.Navigation
import com.parg3v.ticketsearchapp.view.CommonViewModel

@Composable
fun LoadScaffold(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showBottomSheet: MutableState<Boolean>
) {

    val commonViewModel: CommonViewModel = hiltViewModel()

    val fromFieldValue by commonViewModel.fromFieldState.collectAsStateWithLifecycle()
    val toFieldValue by commonViewModel.toFieldState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        commonViewModel.getFromFieldValue(context = context)
    }


    CustomScaffold(
        modifier = modifier,
        navController = navController,
        fromFieldStateProvider = { fromFieldValue.data },
        fromFieldInputChange = commonViewModel::validateFromField,
        toFieldStateProvider = { toFieldValue },
        toFieldInputChange = commonViewModel::validateToField,
        showBottomSheet = showBottomSheet
    ) { paddingValues ->
        Navigation(
            navController = navController,
            paddingValues = paddingValues,
            showBottomSheet = showBottomSheet,
            toFieldStateProvider = { toFieldValue },
            fromFieldStateProvider = { fromFieldValue.data },
            toFieldInputChange = commonViewModel::validateToField,
            fromFieldInputChange = commonViewModel::validateFromField
        )
    }
}