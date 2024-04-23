package com.parg3v.ticketsearchapp.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class HintItem(
    val icon: Painter,
    val title: String,
    val containerColor: Color
)