package com.parg3v.ticketsearchapp.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
    fromFieldInputChange: (String) -> Unit,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit,
    startIcon: Painter? = null,
    startIconTint: Color = Black,
    startIconAction: () -> Unit = {},
    onToFocused: () -> Unit = {},
    leadingIconFrom: Painter? = null,
    trailingIconFrom: Painter? = null,
    leadingIconTo: Painter? = null,
    trailingIconTo: Painter? = null,
    leadingIconFromTint: Color? = null,
    trailingIconFromTint: Color? = null,
    leadingIconToTint: Color? = null,
    trailingIconToTint: Color? = null,
    isToFieldEnabled: Boolean = true,
    isFromFieldEnabled: Boolean = true,
    trailingIconFromAction: () -> Unit = {},
    trailingIconToAction: () -> Unit = {},
    onFromFieldDoneAction: () -> Unit = {},
    onToFieldDoneAction: () -> Unit = {}
) {

    val interactionSource = remember { MutableInteractionSource() }

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
            Icon(
                painter = startIcon,
                contentDescription = null,
                tint = startIconTint,
                modifier = Modifier.clickable(
                    interactionSource = interactionSource, indication = null
                ) { startIconAction() }
            )
        }

        Column(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_airlines_search_spacer_start))
        ) {
            SearchTextField(
                value = fromFieldValueProvider,
                onValueChange = fromFieldInputChange,
                placeholder = stringResource(R.string.where_from_placeholder),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = leadingIconFrom,
                leadingIconTint = leadingIconFromTint,
                trailingIcon = trailingIconFrom,
                trailingIconTint = trailingIconFromTint,
                trailingIconAction = trailingIconFromAction,
                onDoneAction = onFromFieldDoneAction,
                onFocused = onToFocused,
                enabled = isFromFieldEnabled
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
                placeholder = stringResource(R.string.where_to_placeholder),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = leadingIconTo,
                leadingIconTint = leadingIconToTint,
                trailingIcon = trailingIconTo,
                trailingIconTint = trailingIconToTint,
                trailingIconAction = trailingIconToAction,
                onDoneAction = onToFieldDoneAction,
                onFocused = onToFocused,
                enabled = isToFieldEnabled
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CustomSearchBar(
        fromFieldValueProvider = { "" },
        fromFieldInputChange = { _ -> },
        toFieldStateProvider = { "" },
        toFieldInputChange = { },
        startIcon = painterResource(id = R.drawable.search_icon),
    )
}