package com.project.albus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.project.albus.R
import com.project.albus.databinding.FragmentCreateBatchBinding
import kotlinx.android.synthetic.main.fragment_batch.*
import kotlinx.android.synthetic.main.fragment_batch.createBtn
import kotlinx.android.synthetic.main.fragment_create_batch.*
import java.util.*



class CreateFragment : Fragment() {

    lateinit var binding: FragmentCreateBatchBinding
    private lateinit var  mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(this).get()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_batch, container, false)
        binding.lifecycleOwner = this
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        createBtn.setOnClickListener {
            if(inputCode_text.text!=null){
                mainViewModel.saveModel(inputCode_text.text.toString())

            }else{
                inputCode_text.error="must be filled"
            }
        }
    }

}