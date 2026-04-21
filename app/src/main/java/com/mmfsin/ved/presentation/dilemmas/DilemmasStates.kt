package com.mmfsin.ved.presentation.dilemmas

import com.mmfsin.ved.domain.models.Dilemma

data class DilemmasStates(
    val dilemma: Dilemma = Dilemma(),
    val isLoading: Boolean = false,
    val showVotesResult: Boolean = false,
    val voteResult: Boolean? = null,
)