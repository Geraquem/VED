package com.mmfsin.ved.domain.interfaces

import com.mmfsin.ved.domain.models.Dilemma

interface IDilemmasRepository {
    suspend fun getDilemmas(): List<Dilemma>?
}