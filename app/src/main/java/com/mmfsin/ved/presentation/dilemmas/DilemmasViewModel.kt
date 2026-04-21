package com.mmfsin.ved.presentation.dilemmas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmfsin.ved.domain.usecases.GetDilemmaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DilemmasViewModel @Inject constructor(
    val getDilemmaUseCase: GetDilemmaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DilemmasStates())
    val uiState: StateFlow<DilemmasStates> = _uiState

    fun getDilemma() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val dilemma = getDilemmaUseCase()
            withContext(Dispatchers.Main) {
                dilemma?.let { d ->
                    _uiState.update { it.copy(dilemma = d, isLoading = false) }
                } ?: run {
                    /** ERRORRRR */
                    _uiState.update { it.copy() }
                }
            }
        }
    }

    fun voteClickButton(votedYes: Boolean) {
        _uiState.update {
            it.copy(
                showVotesResult = true,
                voteResult = votedYes
            )
        }
    }
}