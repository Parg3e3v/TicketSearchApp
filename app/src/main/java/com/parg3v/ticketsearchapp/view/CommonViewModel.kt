package com.parg3v.ticketsearchapp.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.Result
import com.parg3v.domain.use_cases.GetFromFieldFromDataStoreUseCase
import com.parg3v.domain.use_cases.SaveFromFieldToDataStoreUseCase
import com.parg3v.domain.use_cases.ValidateCyrillicTextUseCase
import com.parg3v.ticketsearchapp.model.FromFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val validateCyrillicTextUseCase: ValidateCyrillicTextUseCase,
    private val getFromFieldFromDataStoreUseCase: GetFromFieldFromDataStoreUseCase,
    private val saveFromFieldToDataStoreUseCase: SaveFromFieldToDataStoreUseCase
) : ViewModel() {
    private val _fromFieldState = MutableStateFlow(FromFieldState())
    val fromFieldState: StateFlow<FromFieldState> = _fromFieldState.asStateFlow()

    private val _toFieldState = MutableStateFlow(String())
    val toFieldState: StateFlow<String> = _toFieldState.asStateFlow()

    fun validateFromField(input: String, context: Context) {
        if (validateCyrillicTextUseCase(input)) {
            saveFromFieldValue(context = context, input)
            _fromFieldState.update { FromFieldState(data = input) }
        }
    }

    fun validateToField(input: String) {
        if (validateCyrillicTextUseCase(input)) _toFieldState.update { input }
    }

    suspend fun getFromFieldValue(context: Context) {
        _fromFieldState.value = FromFieldState(data = getFromFieldFromDataStoreUseCase(context))
    }
    private fun saveFromFieldValue(context: Context, value: String) {
        saveFromFieldToDataStoreUseCase(
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
}