package com.mmfsin.ved.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.mmfsin.ved.domain.interfaces.IDilemmasRepository
import com.mmfsin.ved.domain.models.Dilemma
import com.mmfsin.ved.utils.CREATOR_NAME
import com.mmfsin.ved.utils.DILEMMAS
import com.mmfsin.ved.utils.TXT_BOTTOM
import com.mmfsin.ved.utils.TXT_TOP
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DilemmasRepository @Inject constructor() : IDilemmasRepository {

    override suspend fun getDilemmas(): List<Dilemma>? = suspendCancellableCoroutine { cont ->
        val result = mutableListOf<Dilemma>()

        val db = FirebaseFirestore.getInstance()
        db.collection(DILEMMAS).get().addOnSuccessListener { documents ->
            for (doc in documents) {
                val data = Dilemma(
                    topText = doc.getString(TXT_TOP) ?: "",
                    bottomText = doc.getString(TXT_BOTTOM) ?: "",
                    creator = doc.getString(CREATOR_NAME) ?: "",
                    votesYes = 456,
                    votesNo = 851
                )
                result.add(data)
            }
            cont.resume(result)
        }.addOnFailureListener {
            cont.resume(null)
        }
    }
}