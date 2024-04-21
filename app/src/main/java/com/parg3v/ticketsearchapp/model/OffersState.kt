package com.parg3v.ticketsearchapp.model

import com.parg3v.domain.common.RootError
import com.parg3v.domain.model.Offer

data class OffersState(
    val isLoading: Boolean = false,
    val data: List<Offer>? = null,
    val error: RootError? = null
)