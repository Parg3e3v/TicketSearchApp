package com.parg3v.ticketsearchapp.view.specificSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.Result
import com.parg3v.domain.use_cases.GetAllTicketOffersUseCase
import com.parg3v.ticketsearchapp.model.TicketOffersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SpecificSearchViewModel @Inject constructor(
    private val getAllTicketOffersUseCase: GetAllTicketOffersUseCase
) : ViewModel() {

    private val _ticketOffersState = MutableStateFlow(TicketOffersState())
    val ticketOffersState: StateFlow<TicketOffersState> = _ticketOffersState.asStateFlow()
    fun getAllTicketOffers() {
        getAllTicketOffersUseCase().onEach { result ->
            when (result) {
                is Result.Error -> {
                    _ticketOffersState.value = TicketOffersState(error = result.error)
                }

                is Result.Loading -> {
                    _ticketOffersState.value = TicketOffersState(isLoading = true)
                }
                is Result.Success -> {
                    _ticketOffersState.value = TicketOffersState(data = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}