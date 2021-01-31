package com.project.albus.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.google.firebase.auth.FirebaseAuth
import com.project.albus.data.BatchRepository
import java.util.*
import com.google.firebase.ktx.Firebase
import com.project.albus.data.BatchDetails

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "FIRESTORE_VIEW_MODEL"
    private val batchRepo: BatchRepository = BatchRepository(getApplication())

    var savedBatchDetails : MutableLiveData<List<BatchDetails>> = MutableLiveData()

    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isSaved= MutableLiveData<Boolean>(false)
    val isSaved: LiveData<Boolean> = _isSaved

    val code : MutableLiveData<String> = MutableLiveData()
    val batchName : MutableLiveData<String> = MutableLiveData()

    fun saveModel() {
        val alphabet = charArrayOf('A','B','C','D','E','F','G', 'H' ,'I' ,'J' ,'K' ,'L' ,'M' ,'N' ,'O' ,'P' ,'Q', 'R', 'S' ,
            'T' ,'U', 'V', 'W' ,'X' ,'Y' ,'Z', '0' ,'1', '2' ,'3' ,'4' ,'5' ,'6', '7' ,'8' ,'9')
        val random = Random()
        val _code = NanoIdUtils.randomNanoId(random, alphabet, 6)

        if (_code!=null) {
            code.postValue(_code)
            batchRepo.saveBatch(batchName.value!!, _code)?.addOnFailureListener {
                Log.e(TAG,"Failed to save Batch!")
                _isSaved.postValue(false)
            }
                    ?.addOnSuccessListener {
                        Log.e(TAG,"Success to save Batch!")
                        _isSaved.postValue(true)
                    }

        }
    }
}


