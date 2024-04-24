package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.model.HintItem
import com.parg3v.ticketsearchapp.ui.theme.Red

@Composable
fun HintCard(modifier: Modifier = Modifier, item: HintItem, onClick: () -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = item.icon, contentDescription = null,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        dimensionResource(id = R.dimen.hint_card_icon_radius)
                    )
                )
                .size(48.dp)
                .background(item.containerColor)
                .padding(dimensionResource(id = R.dimen.hint_card_icon_inner_padding)),
            tint = Color.White
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.hint_card_text_padding_top))
        )
    }
}

@Preview
@Composable
private fun Preview() {
    HintCard(
        item = HintItem(
            icon = painterResource(id = R.drawable.fire_icon),
            title = stringResource(id = R.string.hot_tickets),
            containerColor = Red
        )
    )
}