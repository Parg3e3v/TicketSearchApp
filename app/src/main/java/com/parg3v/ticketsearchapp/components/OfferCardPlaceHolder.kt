package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Grey6

@Composable
fun OfferCardPlaceHolder(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.offer_card_image_size)
                )
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.offer_card_image_radius)))
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(top = dimensionResource(id = R.dimen.offer_card_text_top_padding))
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(top = dimensionResource(id = R.dimen.offer_card_text_top_padding))
                .shimmerEffect()
        )
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.offer_card_text_top_padding))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.airline_tickets_icon),
                contentDescription = null,
                tint = Grey6,
                modifier = Modifier.size(dimensionResource(id = R.dimen.offer_card_price_icon_size))
            )

            Box(modifier = Modifier
                .width(100.dp)
                .height(24.dp)
                .shimmerEffect()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OfferCardPlaceHolder(
        modifier = Modifier
    )
}