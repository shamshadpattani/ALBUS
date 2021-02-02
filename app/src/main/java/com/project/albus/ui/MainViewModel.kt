package com.project.albus.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.project.albus.data.BatchDetails
import com.project.albus.data.BatchRepository
import com.project.albus.data.ScheduleData
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "FIRESTORE_VIEW_MODEL"
    private val batchRepo: BatchRepository = BatchRepository(getApplication())


    private val _savedBatchDetailsList = MutableLiveData<List<BatchDetails>?>()
    val savedBatchDetailsList: MutableLiveData<List<BatchDetails>?> = _savedBatchDetailsList

    private val _batchDetails = MutableLiveData<BatchDetails?>()
    val batchDetails: MutableLiveData<BatchDetails?> = _batchDetails


    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isSaved = MutableLiveData<Boolean>(false)
    val isSaved: LiveData<Boolean> = _isSaved

    val code: MutableLiveData<String> = MutableLiveData()
    val batchName: MutableLiveData<String> = MutableLiveData()

    val subjectName: MutableLiveData<String> = MutableLiveData()
    val date: MutableLiveData<String> = MutableLiveData()
    val location: MutableLiveData<String> = MutableLiveData()
    val note: MutableLiveData<String> = MutableLiveData()

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
                _savedBatchDetailsList.value = null
                return@addSnapshotListener
            }

            var savedBatchDetails: MutableList<BatchDetails> = mutableListOf()
            for (doc in value!!) {
                    //var batchItems = doc.toObject(BatchDetails::class.java)
                        var d=doc.data
                var c=d.values
                var batchItems = doc.toObject(BatchDetails::class.java)
                    savedBatchDetails.add(batchItems)
            }
            _savedBatchDetailsList.value = savedBatchDetails
        }
        return _savedBatchDetailsList
    }

    //get schedules page
    fun getDetails(code: String) {
        batchRepo.getDetails(code).addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                _batchDetails.value = null
                return@addSnapshotListener
            }
              var savedbatchDetails: BatchDetails? = value?.toObject(BatchDetails::class.java)
            _batchDetails.value = savedbatchDetails
        }
    }

    fun updateSchedules(code: String) {
       var scheduleData:List<ScheduleData> = listOf(ScheduleData(name = subjectName.value, note = note.value, location = location.value, date = date.value))
        batchRepo.update(scheduleData, code)?.addOnFailureListener {
            Log.e(TAG, "Failed to save Batch!$it")
            _isSaved.postValue(false)
        }
            ?.addOnSuccessListener {
                Log.e(TAG, "Success to save Batch!")
                _isSaved.postValue(true)
            }
    }

}


