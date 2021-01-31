package com.project.albus.data


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn



class BatchRepository(c: Application) {


    private var mContext: Application = c
    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var owner = FirebaseAuth.getInstance().currentUser?.displayName

    fun saveBatch(name: String,code: String): Task<Void>? {
            var batchDetails: BatchDetails? = owner?.let { BatchDetails(name = name,code = code,owner = it) }
        var documentReference = firestoreDB.collection("Batch").document(code)
        return batchDetails?.let { documentReference.set(it) }

            /*  try {
            val db = FirebaseFirestore.getInstance().collection("Batch").document(code)
            val items = HashMap<String, Any>()
            items.put("name", name)
            items.put("owner", owner.toString())
            items.put("code", code)
            db.set(items).addOnSuccessListener {

            }.addOnFailureListener {

            }
        }catch (e:Exception)
        {

       }*/
    }

}


