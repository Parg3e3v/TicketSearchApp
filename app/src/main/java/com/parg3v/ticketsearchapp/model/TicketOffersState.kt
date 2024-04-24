package com.parg3v.ticketsearchapp.model

import com.parg3v.domain.common.RootError
import com.parg3v.domain.model.TicketOffer

data class TicketOffersState (
    val isLoading: Boolean = false,
    val data: List<TicketOffer>? = null,
    val error: RootError? = null
)