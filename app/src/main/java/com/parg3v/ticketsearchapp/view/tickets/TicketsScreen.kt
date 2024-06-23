package com.parg3v.ticketsearchapp.view.tickets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.components.Shimmer
import com.parg3v.ticketsearchapp.components.TicketItem
import com.parg3v.ticketsearchapp.components.TicketItemPlaceHolder
import com.parg3v.ticketsearchapp.model.TicketsState
import com.parg3v.ticketsearchapp.ui.theme.Blue
import com.parg3v.ticketsearchapp.ui.theme.Grey2
import com.parg3v.ticketsearchapp.ui.theme.Grey6

@Composable
fun TicketsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    ticketsState: TicketsState,
    dateProvider: () -> String,
    passengersProvider: () -> String,
    fromProvider: () -> String,
    toProvider: () -> String
) {

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.ticket_screen_top_padding),
                        bottom = dimensionResource(id = R.dimen.ticket_screen_top_bar_bottom_padding)
                    )
                    .background(Grey2)
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.ticket_screen_top_bar_inner_padding))
            ) {
                Icon(
                    modifier = Modifier.clickable { navController.popBackStack() },
                    painter = painterResource(id = R.drawable.left_arrow_icon),
                    contentDescription = null,
                    tint = Blue
                )
                Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.ticket_screen_top_bar_text_start_padding))) {
                    Text(
                        text = "${fromProvider().trimEnd()}-${toProvider().trimEnd()}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_screen_top_bar_subtitle_top_padding)),
                        text = "${dateProvider()}, ${passengersProvider()}",
                        style = MaterialTheme.typography.headlineMedium.copy(color = Grey6)
                    )
                }

            }

            Shimmer(isLoading = ticketsState.isLoading, contentAfterLoading = {
                LazyColumn {

                    if (ticketsState.data.isNullOrEmpty()) {
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = stringResource(R.string.isempty),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    } else {
                        items(ticketsState.data) { ticket ->
                            TicketItem(
                                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_screen_tickets_padding)),
                                ticket = ticket
                            )
                        }

                    }
                }
            }, loadingComposable = {
                LazyColumn {
                    items(8) {
                        TicketItemPlaceHolder(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_screen_tickets_padding)))
                    }
                }
            })
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.ticket_screen_bottom_bar_section_bottom_padding))
                .align(Alignment.BottomCenter)
                .background(
                    color = Blue,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.common_button_radius))
                )
                .padding(dimensionResource(id = R.dimen.ticket_screen_bottom_bar_section_inner_padding))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter_icon),
                contentDescription = null,
                tint = Color.White
            )
            Text(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.ticket_screen_bottom_bar_text_padding_start)),
                text = stringResource(id = R.string.filter),
                style = MaterialTheme.typography.headlineMedium
            )
            Icon(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.ticket_screen_bottom_bar_section_padding)),
                painter = painterResource(id = R.drawable.graph_icon),
                contentDescription = null,
                tint = Color.White
            )
            Text(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.ticket_screen_bottom_bar_text_padding_start)),
                text = stringResource(id = R.string.prices_graph),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}