package com.parg3v.ticketsearchapp.view.specificSearch

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.components.CustomSearchBar
import com.parg3v.ticketsearchapp.components.SmallButton
import com.parg3v.ticketsearchapp.components.TicketOffersColumn
import com.parg3v.ticketsearchapp.model.TicketOffersState
import com.parg3v.ticketsearchapp.navigation.Screen
import com.parg3v.ticketsearchapp.ui.theme.Blue
import com.parg3v.ticketsearchapp.ui.theme.Grey5
import com.parg3v.ticketsearchapp.ui.theme.Grey6
import com.parg3v.ticketsearchapp.ui.theme.Grey7
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecificSearchScreen(
    navController: NavController,
    fromFieldStateProvider: () -> String?,
    fromFieldInputChange: (String, Context) -> Unit,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit,
    ticketOffersState: TicketOffersState
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val datePickerState = rememberDatePickerState()
    val dateFormat = SimpleDateFormat("dd MMM, E", Locale("ru", "RU"))
    val dateFormatToPass = SimpleDateFormat("dd MMMM", Locale("ru", "RU"))
    var showDatePicker by remember { mutableStateOf(false) }
    var showDatePickerBack by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(Calendar.getInstance().time) }
    val dateSplit = dateFormat.format(date).split(".")


    val styledText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
            )
        ) {
            append(dateSplit[0])
        }
        withStyle(
            style = SpanStyle(
                color = Grey6,
            )
        ) {
            append(dateSplit[1])
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }

                        if (selectedDate.after(Calendar.getInstance())) {
                            date = selectedDate.time
                            showDatePicker = false
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.date_error),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            DatePicker(state = datePickerState)
        }
    }

    if (showDatePickerBack) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerBack = false },
            confirmButton = {}
        )
        {
            DatePicker(state = datePickerState)
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        CustomSearchBar(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.specsearch_bar_padding_top)),
            fromFieldValueProvider = fromFieldStateProvider,
            fromFieldInputChange = fromFieldInputChange,
            toFieldStateProvider = toFieldStateProvider,
            toFieldInputChange = toFieldInputChange,
            startIcon = painterResource(id = R.drawable.left_arrow_icon),
            startIconTint = Color.White,
            startIconAction = { navController.popBackStack() },
            trailingIconFrom = painterResource(id = R.drawable.change_icon),
            trailingIconTo = painterResource(id = R.drawable.close_icon),
            trailingIconFromTint = Color.White,
            trailingIconToTint = Grey7,
            trailingIconFromAction = {
                val fromFieldCurrentText = fromFieldStateProvider() ?: ""
                fromFieldInputChange(toFieldStateProvider(), context)
                toFieldInputChange(fromFieldCurrentText)
            },
            trailingIconToAction = { toFieldInputChange("") }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.ticket_offer_small_button_space_between)),
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(top = dimensionResource(id = R.dimen.ticket_offer_small_button_top_padding))
        ) {
            SmallButton(
                leadingIcon = painterResource(id = R.drawable.plus_icon),
                text = stringResource(R.string.back),
                onClick = { showDatePickerBack = true }
            )

            SmallButton(
                text = styledText,
                onClick = { showDatePicker = true })
            SmallButton(
                leadingIcon = painterResource(id = R.drawable.profile_icon),
                text = stringResource(R.string.temp_passangers_count),
                leadingIconTint = Grey5
            )
            SmallButton(
                leadingIcon = painterResource(id = R.drawable.filter_icon),
                text = stringResource(R.string.filters),
                leadingIconTint = Color.White
            )
        }

        ticketOffersState.data?.let {
            TicketOffersColumn(
                ticketOffers = it,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ticket_offers_section_top))
            )
        }

        Button(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.ticket_offer_button_top_padding))
                .fillMaxWidth()
                .aspectRatio(7.8F),
            onClick = {
                if (toFieldStateProvider().isNotBlank() && !fromFieldStateProvider().isNullOrBlank()) {
                    navController.navigate(
                        Screen.TicketsScreen.withArgs(
                            dateFormatToPass.format(date),
                            "1 пассажир",
                            fromFieldStateProvider() ?: "",
                            toFieldStateProvider()
                        )
                    )
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.no_data_error), Toast.LENGTH_SHORT
                    ).show()
                }
            },
            colors = ButtonColors(
                containerColor = Blue,
                contentColor = Color.White,
                disabledContainerColor = Blue,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.ticket_offer_button_radius))
        ) {
            Text(
                text = stringResource(R.string.see_all_tickets),
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp)
            )
        }
    }
}