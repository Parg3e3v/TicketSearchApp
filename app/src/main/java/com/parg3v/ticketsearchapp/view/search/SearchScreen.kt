package com.parg3v.ticketsearchapp.view.search

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.components.CustomSearchBar
import com.parg3v.ticketsearchapp.components.HintCard
import com.parg3v.ticketsearchapp.components.RecommendedPlaceCard
import com.parg3v.ticketsearchapp.model.HintItem
import com.parg3v.ticketsearchapp.model.RecommendedPlaceItem
import com.parg3v.ticketsearchapp.navigation.Screen
import com.parg3v.ticketsearchapp.ui.theme.Blue
import com.parg3v.ticketsearchapp.ui.theme.DarkBlue
import com.parg3v.ticketsearchapp.ui.theme.Green
import com.parg3v.ticketsearchapp.ui.theme.Grey3
import com.parg3v.ticketsearchapp.ui.theme.Grey6
import com.parg3v.ticketsearchapp.ui.theme.Grey7
import com.parg3v.ticketsearchapp.ui.theme.Red
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navController: NavController,
    fromFieldStateProvider: () -> String?,
    fromFieldInputChange: (String, Context) -> Unit,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit,
    closeBottomSheet: suspend () -> Unit
) {
    val scope = rememberCoroutineScope()

    val placeItems = listOf(
        RecommendedPlaceItem(
            image = painterResource(id = R.drawable.stambul),
            title = stringResource(R.string.stambul),
            subtitle = stringResource(R.string.recommended_place)
        ), RecommendedPlaceItem(
            image = painterResource(id = R.drawable.sochi),
            title = stringResource(R.string.sochi),
            subtitle = stringResource(R.string.recommended_place)
        ), RecommendedPlaceItem(
            image = painterResource(id = R.drawable.pxuket),
            title = stringResource(R.string.pxuket),
            subtitle = stringResource(R.string.recommended_place)
        )
    )

    val hintItems = listOf(
        HintItem(
            icon = painterResource(id = R.drawable.route_icon),
            title = stringResource(id = R.string.hard_route),
            containerColor = Green
        ), HintItem(
            icon = painterResource(id = R.drawable.earth_icon),
            title = stringResource(id = R.string.random),
            containerColor = Blue
        ), HintItem(
            icon = painterResource(id = R.drawable.calendar_icon),
            title = stringResource(id = R.string.holidays),
            containerColor = DarkBlue
        ), HintItem(
            icon = painterResource(id = R.drawable.fire_icon),
            title = stringResource(id = R.string.hot_tickets),
            containerColor = Red
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.search_screen_padding))
    ) {
        CustomSearchBar(
            fromFieldValueProvider = fromFieldStateProvider,
            fromFieldInputChange = fromFieldInputChange,
            toFieldStateProvider = toFieldStateProvider,
            toFieldInputChange = toFieldInputChange,
            leadingIconFrom = painterResource(id = R.drawable.plane_icon),
            leadingIconTo = painterResource(id = R.drawable.search_icon),
            trailingIconTo = painterResource(id = R.drawable.close_icon),
            leadingIconFromTint = Grey6,
            leadingIconToTint = Color.White,
            trailingIconToTint = Grey7,
            trailingIconToAction = { toFieldInputChange("") },
            onToFieldDoneAction = {
                navigateAndClose(
                    scope,
                    navController,
                    closeBottomSheet,
                    Screen.SpecificSearchScreen.route
                )
            },
        )
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.search_screen_hints_padding_top))
        ) {
            hintItems.forEach { item ->
                val onClick: () -> Unit = if (item.title != stringResource(id = R.string.random)) {
                    {
                        navigateAndClose(
                            scope,
                            navController,
                            closeBottomSheet,
                            Screen.ToDoScreen.withArgs(item.title)
                        )
                    }
                } else {
                    {
                        toFieldInputChange(item.title)
                        navigateAndClose(
                            scope,
                            navController,
                            closeBottomSheet,
                            Screen.SpecificSearchScreen.route
                        )
                    }
                }

                HintCard(
                    item = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = onClick
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.search_screen_places_space)),
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.search_screen_places_padding_top))
                .background(
                    color = Grey3, shape = RoundedCornerShape(
                        dimensionResource(id = R.dimen.from_to_where_search_radius)
                    )
                )
                .padding(dimensionResource(id = R.dimen.recommended_place_card_padding))
        ) {
            itemsIndexed(placeItems) { index, item ->
                val modifier =
                    if (index < placeItems.size - 1) Modifier.padding(bottom = dimensionResource(id = R.dimen.recommended_place_card_padding_between))
                    else Modifier
                RecommendedPlaceCard(
                    modifier = modifier,
                    item = item,
                    onclick = {
                        toFieldInputChange(item.title)
                        navigateAndClose(
                            scope,
                            navController,
                            closeBottomSheet,
                            Screen.SpecificSearchScreen.route
                        )
                    })
            }
        }
    }
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