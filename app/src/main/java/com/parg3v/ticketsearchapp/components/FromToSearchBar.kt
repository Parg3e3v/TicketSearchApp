package com.parg3v.ticketsearchapp.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Black
import com.parg3v.ticketsearchapp.ui.theme.Grey3
import com.parg3v.ticketsearchapp.ui.theme.Grey4
import com.parg3v.ticketsearchapp.ui.theme.Grey5

@Composable
fun FromToSearchBar(
    modifier: Modifier = Modifier,
    fromFieldValueProvider: () -> String?,
    fromFieldInputChange: (String, Context) -> Unit,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .background(
                color = Grey3, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.from_to_where_search_radius)
                )
            )
            .padding(dimensionResource(id = R.dimen.from_to_where_search_inner_padding))
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Grey4, shape = RoundedCornerShape(
                        dimensionResource(id = R.dimen.from_to_where_search_radius)
                    )
                )
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.from_to_where_search_padding_all),
                    end = dimensionResource(id = R.dimen.from_to_where_search_padding_all),
                    bottom = dimensionResource(id = R.dimen.from_to_where_search_padding_all),
                    start = dimensionResource(id = R.dimen.from_to_where_search_padding_start)
                ), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Black),
            )
            Column(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_airlines_search_spacer_start))
            ) {
                SearchTextField(
                    value = fromFieldValueProvider,
                    onValueChange = fromFieldInputChange,
                    placeholder = stringResource(R.string.where_from_placeholder),
                    context = context
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Grey5)
                )
                SearchTextField(
                    value = toFieldStateProvider,
                    onValueChange = toFieldInputChange,
                    placeholder = stringResource(R.string.where_to_placeholder)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FromToSearchBar(fromFieldValueProvider = { "" },
        fromFieldInputChange = { _, _ -> },
        toFieldStateProvider = { "" },
        toFieldInputChange = { })
}