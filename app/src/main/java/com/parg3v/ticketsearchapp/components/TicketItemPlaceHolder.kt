package com.parg3v.ticketsearchapp.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Grey1
import com.parg3v.ticketsearchapp.ui.theme.Grey6
import com.parg3v.ticketsearchapp.ui.theme.Red
import com.parg3v.ticketsearchapp.ui.theme.TicketSearchAppTheme

@Composable
fun TicketItemPlaceHolder(modifier: Modifier = Modifier) {

    Box(modifier = modifier) {

        RoundedBackgroundWithPadding(
            backgroundColor = Grey1,
            cornerRadius = dimensionResource(id = R.dimen.ticket_item_corner_radius)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(24.dp)
                        .shimmerEffect(),
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
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(20.dp)
                                .padding(bottom = 2.dp)
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(20.dp)
                                .shimmerEffect()
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
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(20.dp)
                                .padding(bottom = 2.dp)
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(20.dp)
                                .shimmerEffect()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = dimensionResource(id = R.dimen.ticket_item_add_info_start_padding)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1F))

                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .height(24.dp)
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.weight(5F))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TicketSearchAppTheme {
        TicketItemPlaceHolder()
    }
}