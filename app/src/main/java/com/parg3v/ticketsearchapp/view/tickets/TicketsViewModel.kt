package com.parg3v.ticketsearchapp.view.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.Result
import com.parg3v.domain.use_cases.GetAllTicketsUseCase
import com.parg3v.ticketsearchapp.model.TicketsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val getAllTicketsUseCase: GetAllTicketsUseCase
) : ViewModel() {

    private val _ticketsState = MutableStateFlow(TicketsState())
    val ticketsState: StateFlow<TicketsState> = _ticketsState.asStateFlow()
    fun getAllTickets() {
        getAllTicketsUseCase().onEach { result ->
            when (result) {
                is Result.Error -> {
                    _ticketsState.value = TicketsState(error = result.error)
                }

                is Result.Loading -> {
                    _ticketsState.value = TicketsState(isLoading = true)
                }

                is Result.Success -> {
                    _ticketsState.value = TicketsState(data = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}