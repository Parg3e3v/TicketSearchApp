package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.parg3v.domain.model.Offer
import com.parg3v.domain.model.Price
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.formatWithThousandSeparator
import com.parg3v.ticketsearchapp.ui.theme.Grey6

@Composable
fun OfferCard(modifier: Modifier = Modifier, image: Painter, offer: Offer) {
    Column(modifier = modifier) {
        Image(
            painter = image, contentDescription = null,
            modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.offer_card_image_size)
                )
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.offer_card_image_radius))),

            contentScale = ContentScale.FillHeight,
        )
        Text(
            text = offer.title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.offer_card_text_top_padding)),
        )
        Text(
            text = offer.town,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.offer_card_text_top_padding)),
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
            Text(
                text = stringResource(
                    R.string.ticket_airline_price,
                    formatWithThousandSeparator(offer.price.value)
                ),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OfferCard(
        modifier = Modifier,
        image = painterResource(id = R.drawable.id_1),
        offer = Offer(1, "Title", "town", Price(10000))
    )
}