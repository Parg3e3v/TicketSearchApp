package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.parg3v.ticketsearchapp.model.BottomNavItem
import com.parg3v.ticketsearchapp.ui.theme.Grey2
import com.parg3v.ticketsearchapp.view.search.SearchScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.AirlineTickets,
        BottomNavItem.Hotels,
        BottomNavItem.InShort,
        BottomNavItem.Subscriptions,
        BottomNavItem.Profile
    ),
    showBottomSheet: MutableState<Boolean>,
    fromFieldStateProvider: () -> String?,
    fromFieldInputChange: (String) -> Unit,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Box(modifier = modifier) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    items = items,
                    navController = navController,
                    modifier = Modifier,
                    onItemClick = { navController.navigate(it.route) }
                )
            }
        ) { paddingValues ->
            content(paddingValues)
            if (showBottomSheet.value) {
                ModalBottomSheet(
                    modifier = Modifier.statusBarsPadding(),
                    containerColor = Grey2,
                    onDismissRequest = {
                        showBottomSheet.value = false
                    },
                    sheetState = sheetState
                ) {
                    SearchScreen(
                        navController = navController,
                        fromFieldStateProvider = fromFieldStateProvider,
                        fromFieldInputChange = fromFieldInputChange,
                        toFieldStateProvider = toFieldStateProvider,
                        toFieldInputChange = toFieldInputChange,
                        closeBottomSheet = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet.value = false
                                }
                            }
                        })
                }
            }
        }
    }
}