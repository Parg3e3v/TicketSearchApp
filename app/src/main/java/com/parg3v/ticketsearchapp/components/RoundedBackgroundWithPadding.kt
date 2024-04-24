package com.parg3v.ticketsearchapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Grey3

@Composable
fun RoundedBackgroundWithPadding(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Grey3,
    content: @Composable() (BoxScope.() -> Unit)
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.from_to_where_search_radius)
                )
            )
            .padding(dimensionResource(id = R.dimen.from_to_where_search_inner_padding)),
        content = content
    )
}