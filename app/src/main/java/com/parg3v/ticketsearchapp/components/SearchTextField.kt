package com.parg3v.ticketsearchapp.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.ui.theme.Grey6
import com.parg3v.ticketsearchapp.ui.theme.TicketSearchAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: () -> String?,
    onValueChange: (String, Context) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    onFocused: () -> Unit = {},
    enabled: Boolean = true,
    context: Context,
    leadingIcon: Painter? = null,
    leadingIconTint: Color? = null,
    trailingIcon: Painter? = null,
    trailingIconTint: Color? = null,
    trailingIconAction: () -> Unit = {},
    onDoneAction: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value() ?: "",
        onValueChange = { onValueChange(it, context) },
        enabled = enabled,
        modifier = modifier.padding(paddingValues = PaddingValues(0.dp))
            .clickable(
                interactionSource = interactionSource, indication = null
            ) { onFocused() },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneAction()
                keyboardController?.hide()
            }),
        textStyle = MaterialTheme.typography.labelMedium.copy(color = Color.White),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    ) {
        TextFieldDefaults.DecorationBox(value = value() ?: "",
            innerTextField = it,
            singleLine = true,
            leadingIcon = if (leadingIcon != null && leadingIconTint != null) {
                {
                    Icon(
                        painter = leadingIcon, contentDescription = null, tint = leadingIconTint
                    )
                }
            } else null,
            trailingIcon = if (trailingIcon != null && trailingIconTint != null) {
                {
                    Icon(
                        painter = trailingIcon,
                        contentDescription = null,
                        tint = trailingIconTint,
                        modifier = Modifier.clickable { trailingIconAction() }
                    )
                }
            } else null,
            enabled = true,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                0.dp,
                dimensionResource(id = R.dimen.search_text_field_text_padding_horizontal),
                0.dp,
                dimensionResource(id = R.dimen.search_text_field_text_padding_horizontal)
            ),
            colors = TextFieldDefaults.colors(
                unfocusedPlaceholderColor = Grey6,
                focusedPlaceholderColor = Grey6,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                selectionColors = TextSelectionColors(
                    handleColor = Color.White, backgroundColor = Grey6
                )
            ),
            placeholder = { Text(placeholder, style = MaterialTheme.typography.labelMedium) },
            isError = isError
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: () -> String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    onFocused: () -> Unit = {},
    enabled: Boolean = true,
    leadingIcon: Painter? = null,
    leadingIconTint: Color? = null,
    trailingIcon: Painter? = null,
    trailingIconTint: Color? = null,
    trailingIconAction: () -> Unit = {},
    onDoneAction: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value() ?: "",
        onValueChange = { onValueChange(it) },
        enabled = enabled,
        modifier = modifier
            .padding(paddingValues = PaddingValues(0.dp))
            .clickable(
                interactionSource = interactionSource, indication = null
            ) { onFocused() },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneAction()
                keyboardController?.hide()
            }),
        textStyle = MaterialTheme.typography.labelMedium.copy(color = Color.White),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
    ) {
        TextFieldDefaults.DecorationBox(value = value() ?: "",
            innerTextField = it,
            singleLine = true,
            leadingIcon = if (leadingIcon != null && leadingIconTint != null) {
                {
                    Icon(
                        painter = leadingIcon, contentDescription = null, tint = leadingIconTint
                    )
                }
            } else null,
            trailingIcon = if (trailingIcon != null && trailingIconTint != null) {
                {
                    Icon(
                        painter = trailingIcon,
                        contentDescription = null,
                        tint = trailingIconTint,
                        modifier = Modifier.clickable { trailingIconAction() }
                    )
                }
            } else null,
            enabled = true,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                0.dp,
                dimensionResource(id = R.dimen.search_text_field_text_padding_horizontal),
                0.dp,
                dimensionResource(id = R.dimen.search_text_field_text_padding_horizontal)
            ),
            colors = TextFieldDefaults.colors(
                unfocusedPlaceholderColor = Grey6,
                disabledPlaceholderColor = Grey6,
                focusedPlaceholderColor = Grey6,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                selectionColors = TextSelectionColors(
                    handleColor = Color.White, backgroundColor = Grey6
                )
            ),
            placeholder = { Text(placeholder, style = MaterialTheme.typography.labelMedium) },
            isError = isError
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TicketSearchAppTheme {
        SearchTextField(
            value = { "Минск" },
            onValueChange = { _, _ -> },
            context = LocalContext.current
        )
    }
}