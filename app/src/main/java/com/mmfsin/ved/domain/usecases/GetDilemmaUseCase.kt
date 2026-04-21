package com.mmfsin.ved.domain.usecases

import com.mmfsin.ved.domain.interfaces.IDilemmasRepository
import com.mmfsin.ved.domain.models.Dilemma
import javax.inject.Inject

class GetDilemmaUseCase @Inject constructor(
    val repository: IDilemmasRepository
) {
    operator fun invoke(): Dilemma? = repository.getDilemma()
}