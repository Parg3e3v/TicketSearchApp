package com.parg3v.ticketsearchapp.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Black
import com.parg3v.ticketsearchapp.ui.theme.Grey4
import com.parg3v.ticketsearchapp.ui.theme.Grey5

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    fromFieldValueProvider: () -> String?,
    fromFieldInputChange: (String, Context) -> Unit,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit,
    startIcon: Painter? = null,
    onToFocused: () -> Unit = {},
    leadingIconFrom: Painter? = null,
    trailingIconFrom: Painter? = null,
    leadingIconTo: Painter? = null,
    trailingIconTo: Painter? = null,
    leadingIconFromTint: Color? = null,
    trailingIconFromTint: Color? = null,
    leadingIconToTint: Color? = null,
    trailingIconToTint: Color? = null,
    isFromClickable: Boolean = false,
    trailingIconFromAction: () -> Unit = {},
    trailingIconToAction: () -> Unit = {}
) {

    val context = LocalContext.current

    Row(
        modifier = modifier
            .background(
                color = Grey4, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.from_to_where_search_radius)
                )
            )
            .padding(
                top = dimensionResource(id = R.dimen.from_to_where_search_padding_all),
                end = dimensionResource(id = R.dimen.from_to_where_search_padding_all),
                bottom = dimensionResource(id = R.dimen.from_to_where_search_padding_all),
                start = dimensionResource(id = R.dimen.from_to_where_search_padding_start)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        startIcon?.let {
            Image(
                painter = startIcon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Black),
            )
        }

        Column(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_airlines_search_spacer_start))
        ) {
            SearchTextField(
                value = fromFieldValueProvider,
                onValueChange = fromFieldInputChange,
                placeholder = stringResource(R.string.where_from_placeholder),
                context = context,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = leadingIconFrom,
                leadingIconTint = leadingIconFromTint,
                trailingIcon = trailingIconFrom,
                trailingIconTint = trailingIconFromTint,
                trailingIconAction = trailingIconFromAction
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey5)
            )

            if (isFromClickable) {
                SearchTextField(
                    value = toFieldStateProvider,
                    onValueChange = toFieldInputChange,
                    placeholder = stringResource(R.string.where_to_placeholder),
                    modifier = Modifier.fillMaxWidth(),
                    onFocused = onToFocused,
                    enabled = false
                )
            } else {
                SearchTextField(
                    value = toFieldStateProvider,
                    onValueChange = toFieldInputChange,
                    placeholder = stringResource(R.string.where_to_placeholder),
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = leadingIconTo,
                    leadingIconTint = leadingIconToTint,
                    trailingIcon = trailingIconTo,
                    trailingIconTint = trailingIconToTint,
                    trailingIconAction = trailingIconToAction
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CustomSearchBar(
        fromFieldValueProvider = { "" },
        fromFieldInputChange = { _, _ -> },
        toFieldStateProvider = { "" },
        toFieldInputChange = { },
        startIcon = painterResource(id = R.drawable.search_icon)
    )
}