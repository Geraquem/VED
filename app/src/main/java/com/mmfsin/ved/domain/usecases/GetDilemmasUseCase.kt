package com.mmfsin.ved.domain.usecases

import com.mmfsin.ved.domain.interfaces.IDilemmasRepository
import com.mmfsin.ved.domain.models.Dilemma
import javax.inject.Inject

class GetDilemmasUseCase @Inject constructor(
    val repository: IDilemmasRepository
) {
    suspend operator fun invoke(): List<Dilemma>? = repository.getDilemmas()
}