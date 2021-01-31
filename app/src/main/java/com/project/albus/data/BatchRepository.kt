package com.project.albus.data


import android.app.Application
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class BatchRepository(c: Application) {


    private var mContext: Application = c
    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var owner = FirebaseAuth.getInstance().currentUser?.uid

    fun saveBatch(name: String,code: String): Task<Void>? {
        val batchDetails: BatchDetails? =
            owner?.let { BatchDetails(name = name, code = code, owner = it, null, null) }
        val documentReference = firestoreDB.collection("Batch").document(code)
        return batchDetails?.let { documentReference.set(it) }
    }

    fun getSavedBatch(): CollectionReference {
        return firestoreDB.collection("Batch")
    }

    fun getDetails(code: String): DocumentReference {
        return firestoreDB.collection("Batch").document(code)
    }


}


