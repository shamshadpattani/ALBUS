package com.project.albus.ui.batch

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.project.albus.data.BatchRepository
import java.util.*
import com.project.albus.data.BatchDetails

class InviteViewModel(application: Application) : AndroidViewModel(application) {

    private val batchRepo: BatchRepository = BatchRepository(getApplication())

    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    val code: MutableLiveData<String> = MutableLiveData()

    fun joinBatch() {
        code.value?.let {
            _dataLoading.postValue(true)
            batchRepo.addUserToBatch(it)
            _dataLoading.postValue(false)
        }
    }

}


