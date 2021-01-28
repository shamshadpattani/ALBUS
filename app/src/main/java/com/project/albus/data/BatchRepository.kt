package com.project.albus.data


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn



class BatchRepository(c: Application) {


    private var mContext: Application = c

    fun saveBatch(name: String, owner: String, code: String) {
        try {

        val db = FirebaseFirestore.getInstance().collection("Batch").document(code!!)
        val items = HashMap<String, Any>()
        items.put("name", name)
        items.put("owner", owner)
        items.put("code", code)
        db.set(items).addOnSuccessListener {

        }.addOnFailureListener {

        }
    }catch (e:Exception)
    {

       }
    }
}


