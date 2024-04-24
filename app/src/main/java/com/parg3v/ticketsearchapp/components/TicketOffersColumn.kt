package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.parg3v.domain.model.TicketOffer
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Blue
import com.parg3v.ticketsearchapp.ui.theme.Grey1
import com.parg3v.ticketsearchapp.ui.theme.Red

@Composable
fun TicketOffersColumn(modifier: Modifier = Modifier, ticketOffers: List<TicketOffer>) {
    RoundedBackgroundWithPadding(modifier = modifier, backgroundColor = Grey1) {
        val circleColors = listOf(
            Red,
            Blue,
            Color.White
        )
        Column {
            Text(
                text = stringResource(R.string.direct_rails),
                style = MaterialTheme.typography.titleMedium
            )
            for (i in 0..2) {
                TicketOffersItem(
                    item = ticketOffers[i],
                    circleColor = circleColors[i],
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_offer_items_padding))
                )
            }
        }
    }
}