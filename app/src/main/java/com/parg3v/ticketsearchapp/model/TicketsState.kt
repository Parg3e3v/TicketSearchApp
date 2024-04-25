package com.parg3v.ticketsearchapp.model

import com.parg3v.domain.common.RootError
import com.parg3v.domain.model.Ticket

data class TicketsState (
    val isLoading: Boolean = false,
    val data: List<Ticket>? = null,
    val error: RootError? = null
)