package com.parg3v.ticketsearchapp.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.parg3v.domain.model.FlightInfo
import com.parg3v.domain.model.HandLuggage
import com.parg3v.domain.model.Luggage
import com.parg3v.domain.model.Price
import com.parg3v.domain.model.Ticket
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.calculateFlightDuration
import com.parg3v.ticketsearchapp.components.RoundedBackgroundWithPadding
import com.parg3v.ticketsearchapp.formatWithThousandSeparator
import com.parg3v.ticketsearchapp.ui.theme.Blue
import com.parg3v.ticketsearchapp.ui.theme.Grey1
import com.parg3v.ticketsearchapp.ui.theme.Grey6
import com.parg3v.ticketsearchapp.ui.theme.Red
import com.parg3v.ticketsearchapp.ui.theme.TicketSearchAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TicketItem(modifier: Modifier = Modifier, ticket: Ticket) {

    val modifierCard: Modifier
    val modifierCardPrice: Modifier

    if (ticket.badge.isNullOrBlank()) {
        modifierCard = Modifier
        modifierCardPrice = Modifier
    } else {
        modifierCard = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_item_card_badge_padding))
        modifierCardPrice = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_item_price_top_padding))
    }
    val dateFormat = SimpleDateFormat("HH:mm", Locale("ru", "RU"))
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("ru", "RU"))
    val dateDeparture: Date = inputFormat.parse(ticket.departure.date) ?: Date()
    val dateArrival: Date = inputFormat.parse(ticket.arrival.date) ?: Date()

    val flightDurationText = stringResource(
        R.string.flight_duration, calculateFlightDuration(dateDeparture, dateArrival)
    )

    val noTransferText = stringResource(id = R.string.no_transfer)

    val styledText = buildAnnotatedString {
        withStyle(
            style = SpanStyle()
        ) {
            append(flightDurationText)
        }
        if (!ticket.hasTransfer) {
            withStyle(
                style = SpanStyle(
                    color = Grey6,
                )
            ) {
                append("/")
            }
            withStyle(
                style = SpanStyle()
            ) {
                append(noTransferText)
            }
        }

    }

    Box(modifier = modifier) {

        RoundedBackgroundWithPadding(
            modifier = modifierCard,
            backgroundColor = Grey1,
            cornerRadius = dimensionResource(id = R.dimen.ticket_item_corner_radius)
        ) {
            Column {
                Text(
                    modifier = modifierCardPrice,
                    text = stringResource(
                        R.string.ticket_price, formatWithThousandSeparator(ticket.price.value)
                    ),
                    style = MaterialTheme.typography.titleLarge,
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .padding(top = dimensionResource(id = R.dimen.ticket_item_info_from_price_padding))
                ) {
                    Box(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.ticket_offer_circle_size))
                            .clip(CircleShape)
                            .background(color = Red)
                    )
                    Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.ticket_item_from_start_padding))) {
                        Text(
                            text = dateFormat.format(dateDeparture),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = ticket.departure.airport,
                            style = MaterialTheme.typography.headlineMedium.copy(color = Grey6),
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_item_date_top_padding))
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1F))
                        Text(
                            text = "—",
                            color = Grey6,
                            modifier = Modifier
                                .weight(5F)
                                .padding(
                                    start = dimensionResource(id = R.dimen.ticket_item_divider_start_padding),
                                    end = dimensionResource(id = R.dimen.ticket_item_divider_end_padding)
                                ),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.weight(5F))
                    }
                    Column {
                        Text(
                            text = dateFormat.format(dateArrival),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = ticket.arrival.airport,
                            style = MaterialTheme.typography.headlineMedium.copy(color = Grey6),
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_item_date_top_padding))
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = dimensionResource(id = R.dimen.ticket_item_add_info_start_padding)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1F))
                        Text(
                            text = styledText,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .weight(5F)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.weight(5F))
                    }
                }
            }
        }
        if (!ticket.badge.isNullOrBlank()) {
            Text(
                text = ticket.badge!!,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .background(
                        color = Blue,
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.common_button_radius))
                    )
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.ticket_item_badge_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.ticket_item_badge_vertical_padding)
                    )
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TicketSearchAppTheme {
        TicketItem(
            ticket = Ticket(
                id = 101,
                badge = "Badge",
                price = Price(9999),
                providerName = "Provider Name",
                company = "Company",
                departure = FlightInfo("Москва", "2024-02-23T04:15:00", "VKO"),
                arrival = FlightInfo("Сочи", "2024-02-23T08:30:00", "AER"),
                hasTransfer = false,
                hasVisaTransfer = false,
                luggage = Luggage(false, Price(1602)),
                handLuggage = HandLuggage(false, "36x30x27"),
                isReturnable = false,
                isExchangable = true
            )
        )
    }
}