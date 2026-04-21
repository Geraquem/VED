package com.mmfsin.ved.presentation.dilemmas

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmfsin.ved.domain.models.Dilemma
import com.mmfsin.ved.domain.usecases.GetDilemmasUseCase
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
    val getDilemmasUseCase: GetDilemmasUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DilemmasStates())
    val uiState: StateFlow<DilemmasStates> = _uiState

    private val _dilemmas = mutableStateListOf<Dilemma>()
    val dilemmas: List<Dilemma> = _dilemmas

    var currentIndex = 0

    fun getDilemmas() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            //            delay(3000)
            val response = getDilemmasUseCase()
            withContext(Dispatchers.Main) {
                _dilemmas.clear()
                response?.let { r ->
                    _dilemmas.addAll(response)
                    _uiState.update { it.copy(dilemma = r.firstOrNull() ?: Dilemma(), isLoading = false) }
                } ?: run {
                    /** ERRORRRR */
                    //                    _uiState.update { it.copy() }
                }
            }
        }
    }

    fun voteClickButton(votedYes: Boolean) {
        _uiState.update {
            it.copy(
                showVotesResult = true,
                userVoted = votedYes
            )
        }
    }

    fun nextDilemma() {
        if (currentIndex < _dilemmas.size - 1) {
            currentIndex++
            _uiState.update {
                it.copy(
                    dilemma = _dilemmas[currentIndex],
                    showVotesResult = false
                )
            }
        }
    }
}