package com.parg3v.ticketsearchapp.view.airlineTickets

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.parg3v.ticketsearchapp.R
import com.parg3v.ticketsearchapp.components.CustomSearchBar
import com.parg3v.ticketsearchapp.components.OfferCard
import com.parg3v.ticketsearchapp.model.OffersState
import com.parg3v.ticketsearchapp.ui.theme.Grey3
import com.parg3v.ticketsearchapp.ui.theme.Grey7
import com.parg3v.ticketsearchapp.ui.theme.TicketSearchAppTheme

@Composable
fun AirlineTicketsScreen(
    offersStateProvider: () -> OffersState,
    fromFieldStateProvider: () -> String?,
    fromFieldInputChange: (String, Context) -> Unit,
    toFieldStateProvider: () -> String,
    toFieldInputChange: (String) -> Unit,
    showBottomSheet: MutableState<Boolean> = mutableStateOf(false)
) {

    val offerImages = listOf(
        painterResource(id = R.drawable.id_1),
        painterResource(id = R.drawable.id_2),
        painterResource(id = R.drawable.id_3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_airlines_top))
    ) {
        Text(
            text = stringResource(id = R.string.airline_tickets_title),
            style = MaterialTheme.typography.titleLarge.copy(color = Grey7),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_airlines_search_top))
                .background(
                    color = Grey3, shape = RoundedCornerShape(
                        dimensionResource(id = R.dimen.from_to_where_search_radius)
                    )
                )
        ) {
            CustomSearchBar(
                isFromClickable = true,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.from_to_where_search_inner_padding)),
                fromFieldValueProvider = fromFieldStateProvider,
                fromFieldInputChange = fromFieldInputChange,
                toFieldStateProvider = toFieldStateProvider,
                toFieldInputChange = toFieldInputChange,
                startIcon = painterResource(id = R.drawable.search_icon),
                onToFocused = { showBottomSheet.value = !showBottomSheet.value }
            )
        }
        Text(
            text = stringResource(id = R.string.airline_tickets_title_music),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_airlines_music_title_top)),
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.offer_card_space_between)),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.offer_cards_section_top_padding))
        ) {
            offersStateProvider().data?.let { list ->
                items(list) { offer ->
                    OfferCard(image = offerImages[offer.id - 1], offer = offer)
                }
            }
        }

    }
}

@Preview
@Composable
private fun Preview() {
    TicketSearchAppTheme {
        AirlineTicketsScreen({ OffersState() }, { "" }, { _, _ -> }, { "" }, { })
    }
}