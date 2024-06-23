package com.parg3v.ticketsearchapp.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.ShimmingBackground
import com.parg3v.ticketsearchapp.ui.theme.ShimmingForeground

@Composable
fun Shimmer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    loadingComposable: @Composable () -> Unit
) {
    if (isLoading) {
        loadingComposable()
    } else {
        contentAfterLoading()
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = stringResource(id = R.string.text_loading))
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = stringResource(id = R.string.text_loading)
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                ShimmingForeground, ShimmingBackground, ShimmingForeground
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}
