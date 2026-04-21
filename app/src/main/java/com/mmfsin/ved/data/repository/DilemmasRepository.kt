package com.mmfsin.ved.data.repository

import com.mmfsin.ved.domain.interfaces.IDilemmasRepository
import com.mmfsin.ved.domain.models.Dilemma
import javax.inject.Inject

class DilemmasRepository @Inject constructor() : IDilemmasRepository {

    override fun getDilemma(): Dilemma? {
        return Dilemma(
            "Holaaaaaaaa", "bottom textsss", "Jejejej",
            votesYes = 247,
            votesNo = 524
        )
    }
}