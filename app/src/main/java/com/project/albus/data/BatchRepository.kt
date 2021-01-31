package com.project.albus.data


import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await


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


}


