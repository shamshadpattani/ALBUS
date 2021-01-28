package com.project.albus.ui

import android.app.Application
import androidx.lifecycle.*
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.google.firebase.auth.FirebaseAuth
import com.project.albus.data.BatchRepository
import java.util.*
import com.google.firebase.ktx.Firebase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var auth: FirebaseAuth

    private val batchRepo: BatchRepository = BatchRepository(getApplication())


    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isRunning= MutableLiveData<Boolean>(false)
    val isRunning: LiveData<Boolean> = _isRunning

    fun saveModel(name: String) {
        val alphabet = charArrayOf('A','B','C','D','E','F','G', 'H' ,'I' ,'J' ,'K' ,'L' ,'M' ,'N' ,'O' ,'P' ,'Q', 'R', 'S' ,
            'T' ,'U', 'V', 'W' ,'X' ,'Y' ,'Z', '0' ,'1', '2' ,'3' ,'4' ,'5' ,'6', '7' ,'8' ,'9')
        val random = Random()
        val owner =  FirebaseAuth.getInstance().currentUser?.displayName
        val code = NanoIdUtils.randomNanoId(random, alphabet, 6)
        if (owner != null) {
            batchRepo.saveBatch(name,owner,code)
        }
    }
}


