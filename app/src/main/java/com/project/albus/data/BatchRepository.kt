package com.project.albus.data


import android.app.Application
import android.util.Log
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
            owner?.let { BatchDetails(name = name, code = code, owner = it) }
        val documentReference = firestoreDB.collection("Batch").document(code)
        return batchDetails?.let { documentReference.set(it) }
    }

    fun getSavedBatch(): CollectionReference {
        return firestoreDB.collection("Batch")
    }

    fun addUserToBatch(code: String): Boolean {
        val batch =  firestoreDB.collection("Batch").document(code)
            batch.addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                var savedbatchDetails: BatchDetails? = value?.toObject(BatchDetails::class.java)
                savedbatchDetails?.members?.add("User Name")
            }
        return true
    }

    fun getDetails(code: String): DocumentReference {
        return firestoreDB.collection("Batch").document(code)
    }

}


