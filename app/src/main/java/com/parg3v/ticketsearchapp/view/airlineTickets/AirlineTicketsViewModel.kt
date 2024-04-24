package com.parg3v.ticketsearchapp.view.airlineTickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.Result
import com.parg3v.domain.use_cases.GetAllOffersUseCase
import com.parg3v.ticketsearchapp.model.OffersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AirlineTicketsViewModel @Inject constructor(
    private val getAllOffersUseCase: GetAllOffersUseCase
) : ViewModel() {
    private val _offersState = MutableStateFlow(OffersState())
    val offersState: StateFlow<OffersState> = _offersState.asStateFlow()

    init {
        getAllOffers()
    }

    private fun getAllOffers() {
        getAllOffersUseCase().onEach { result ->
            when (result) {
                is Result.Error -> {
                    _offersState.value = OffersState(error = result.error)
                }

                is Result.Loading -> {
                    _offersState.value = OffersState(isLoading = true)
                }

                is Result.Success -> {
                    _offersState.value = OffersState(data = result.data)
                }
            }

        }.launchIn(viewModelScope)
    }
}