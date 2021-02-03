package com.project.albus.ui

import android.app.Application
import androidx.lifecycle.*
import com.project.albus.data.BatchRepository

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


