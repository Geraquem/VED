package com.mmfsin.ved.domain.models

data class Dilemma(
    val topText: String = "",
    val bottomText: String = "",
    val creator: String = "",
    val votesYes: Long = 0,
    val votesNo: Long = 0,
)