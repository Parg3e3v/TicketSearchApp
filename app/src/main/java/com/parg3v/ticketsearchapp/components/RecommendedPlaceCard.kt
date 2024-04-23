package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.model.RecommendedPlaceItem
import com.parg3v.ticketsearchapp.ui.theme.Grey4
import com.parg3v.ticketsearchapp.ui.theme.Grey5

@Composable
fun RecommendedPlaceCard(
    modifier: Modifier = Modifier,
    item: RecommendedPlaceItem,
    onclick: () -> Unit = {}
) {
    Column(modifier = modifier.clickable { onclick() }) {
        Row(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.recommended_place_card_padding_vertical)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = item.image, contentDescription = null,
                modifier = Modifier
                    .size(
                        dimensionResource(id = R.dimen.recommended_place_card_image_size)
                    )
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.recommended_place_card_image_radius))),

                contentScale = ContentScale.FillHeight
            )

            Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.recommended_place_card_padding_title_start))) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Grey5),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.recommended_place_card_subtitle_top_padding))
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
    RecommendedPlaceCard(
        item = RecommendedPlaceItem(
            painterResource(id = R.drawable.sochi),
            stringResource(id = R.string.sochi),
            stringResource(id = R.string.recommended_place)
        )
    )
}