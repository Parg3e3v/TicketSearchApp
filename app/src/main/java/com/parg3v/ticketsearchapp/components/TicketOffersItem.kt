package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.domain.model.Price
import com.parg3v.domain.model.TicketOffer
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Blue
import com.parg3v.ticketsearchapp.ui.theme.Grey4
import com.parg3v.ticketsearchapp.ui.theme.Red

@Composable
fun TicketOffersItem(modifier: Modifier = Modifier, item: TicketOffer, circleColor: Color) {
    Column(modifier = modifier.padding(top = dimensionResource(id = R.dimen.ticket_offers_item_padding_top))) {
        Row {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.ticket_offer_circle_size))
                    .clip(CircleShape)
                    .background(color = circleColor)
            )
            Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ticket_offers_item_padding_circle))) {
                Row {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.weight(1F)
                    )
                    Text(
                        text = stringResource(
                            R.string.ticket_offer_price,
                            formatWithThousandSeparator(item.price.value)
                        ),
                        style = MaterialTheme.typography.headlineMedium.copy(color = Blue)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_icon),
                        contentDescription = null,
                        tint = Blue
                    )
                }

                Text(
                    text = item.time_range.joinToString(" "),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.ticket_offers_item_padding_schedule_top),
                        bottom = dimensionResource(id = R.dimen.ticket_offers_item_padding_schedule_bottom)
                    )
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Grey4)
        )
    }

}

@Preview
@Composable
private fun Preview() {
    TicketOffersItem(
        item = TicketOffer(1, "title", listOf("11:11", "11:11", "11:11", "11:11"), Price(3999)),
        circleColor = Red
    )
}