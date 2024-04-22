package com.parg3v.ticketsearchapp.view.airlineTickets

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.Result
import com.parg3v.domain.use_cases.GetAllOffersUseCase
import com.parg3v.domain.use_cases.GetFromFieldFromDataStore
import com.parg3v.domain.use_cases.SaveFromFieldToDataStore
import com.parg3v.domain.use_cases.ValidateCyrillicText
import com.parg3v.ticketsearchapp.model.FromFieldState
import com.parg3v.ticketsearchapp.model.OffersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AirlineTicketsViewModel @Inject constructor(
    private val getAllOffersUseCase: GetAllOffersUseCase,
    private val getFromFieldFromDataStore: GetFromFieldFromDataStore,
    private val saveFromFieldToDataStore: SaveFromFieldToDataStore,
    private val validateCyrillicText: ValidateCyrillicText
) : ViewModel() {
    private val _offersState = MutableStateFlow(OffersState())
    val offersState: StateFlow<OffersState> = _offersState.asStateFlow()

    private val _fromFieldState = MutableStateFlow(FromFieldState())
    val fromFieldState: StateFlow<FromFieldState> = _fromFieldState.asStateFlow()

    private val _toFieldState = MutableStateFlow(String())
    val toFieldState: StateFlow<String> = _toFieldState.asStateFlow()

    init {
        getAllOffers()
    }

    suspend fun getFromFieldValue(context: Context) {
        _fromFieldState.value = FromFieldState(data = getFromFieldFromDataStore(context))
    }

    fun validateFromField(input: String, context: Context) {
        if (validateCyrillicText(input)) saveFromFieldValue(context = context, input)
        _fromFieldState.update { FromFieldState(data = input) }
    }

    fun validateToField(input: String) {
        if (validateCyrillicText(input)) _toFieldState.update { input }
    }

    private fun saveFromFieldValue(context: Context, value: String) {
        saveFromFieldToDataStore(
            context = context, value = value
        ).onEach { result ->
            when (result) {
                is Result.Error -> {
                    _fromFieldState.value = FromFieldState(data = null)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
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