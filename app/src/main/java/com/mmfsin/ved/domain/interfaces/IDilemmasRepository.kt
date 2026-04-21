package com.mmfsin.ved.domain.interfaces

import com.mmfsin.ved.domain.models.Dilemma

interface IDilemmasRepository {
    fun getDilemma(): Dilemma?
}