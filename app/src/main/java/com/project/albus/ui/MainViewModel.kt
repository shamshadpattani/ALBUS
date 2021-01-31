package com.project.albus.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.project.albus.data.BatchRepository
import java.util.*
import com.google.firebase.ktx.Firebase
import com.project.albus.data.BatchDetails
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "FIRESTORE_VIEW_MODEL"
    private val batchRepo: BatchRepository = BatchRepository(getApplication())


    private val _savedBatchDetails = MutableLiveData<List<BatchDetails>?>()
    val savedBatchDetails: MutableLiveData<List<BatchDetails>?> = _savedBatchDetails


    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isSaved = MutableLiveData<Boolean>(false)
    val isSaved: LiveData<Boolean> = _isSaved

    val code: MutableLiveData<String> = MutableLiveData()
    val batchName: MutableLiveData<String> = MutableLiveData()

    fun saveModel() {
        val alphabet = charArrayOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z',
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9'
        )
        val random = Random()
        val _code = NanoIdUtils.randomNanoId(random, alphabet, 6)

        if (_code != null) {
            code.postValue(_code)
            batchRepo.saveBatch(batchName.value!!, _code)?.addOnFailureListener {
                Log.e(TAG, "Failed to save Batch!")
                _isSaved.postValue(false)
            }
                ?.addOnSuccessListener {
                    Log.e(TAG, "Success to save Batch!")
                    _isSaved.postValue(true)
                }

        }
    }

    fun getBatches(): LiveData<List<BatchDetails>?> {
        batchRepo.getSavedBatch().addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                _savedBatchDetails.value=null
                return@addSnapshotListener
            }

            var savedAddressList : MutableList<BatchDetails> = mutableListOf()
            for (doc in value!!) {
                var addressItem = doc.toObject(BatchDetails::class.java)
                savedAddressList.add(addressItem)
            }
            _savedBatchDetails.value = savedAddressList
        }
        return _savedBatchDetails
        }
}



