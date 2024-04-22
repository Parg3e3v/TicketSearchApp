package com.parg3v.ticketsearchapp.model

import com.parg3v.domain.common.RootError

data class FromFieldState(
    val isLoading: Boolean = false,
    val data: String? = null,
    val error: RootError? = null
)