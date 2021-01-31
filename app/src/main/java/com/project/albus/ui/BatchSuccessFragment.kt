package com.project.albus.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.project.albus.R
import com.project.albus.databinding.FragmentBatchSuccessBindingImpl
import com.project.albus.databinding.FragmentCreateBatchBinding
import kotlinx.android.synthetic.main.fragment_batch_success.*


class BatchSuccessFragment : Fragment() {

    lateinit var binding: FragmentBatchSuccessBindingImpl
    private val  mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      //  mainViewModel = ViewModelProvider(this).get()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_batch_success, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel=mainViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
       // codeText.text="abcd"
    }

    private fun observe() {

    }
}