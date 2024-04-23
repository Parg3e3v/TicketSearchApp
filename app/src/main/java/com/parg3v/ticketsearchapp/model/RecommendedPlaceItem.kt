package com.parg3v.ticketsearchapp.model

import androidx.compose.ui.graphics.painter.Painter

data class RecommendedPlaceItem(
    val image: Painter,
    val title: String,
    val subtitle: String
)