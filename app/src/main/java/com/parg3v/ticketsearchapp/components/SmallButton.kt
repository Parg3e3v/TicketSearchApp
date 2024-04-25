package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.AnnotatedString
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Grey3
import com.parg3v.ticketsearchapp.ui.theme.Grey7

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    leadingIconTint: Color = Grey7,
    text: String = "",
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .background(
                color = Grey3,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.common_button_radius))
            )
            .clickable(interactionSource = interactionSource, indication = null) { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.small_button_padding_horizontal),
                vertical = dimensionResource(id = R.dimen.small_button_padding_vertical)
            )
        ) {
            if (leadingIcon != null) {
                Icon(
                    painter = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.small_button_icon_padding))
                        .size(
                            dimensionResource(id = R.dimen.ticket_offer_small_button_icon_size)
                        ),
                    tint = leadingIconTint
                )
            }
            Text(text = text, style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    leadingIconTint: Color = Grey7,
    text: AnnotatedString = AnnotatedString(""),
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .background(
                color = Grey3,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.common_button_radius))
            )
            .clickable(interactionSource = interactionSource, indication = null) { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.small_button_padding_horizontal),
                vertical = dimensionResource(id = R.dimen.small_button_padding_vertical)
            )
        ) {
            if (leadingIcon != null) {
                Icon(
                    painter = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.small_button_icon_padding)),
                    tint = leadingIconTint
                )
            }
            Text(text = text, style = MaterialTheme.typography.headlineMedium)
        }
    }
}