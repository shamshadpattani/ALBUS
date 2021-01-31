package com.project.albus.ui.batch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.albus.R
import com.project.albus.databinding.FragmentCreateBatchBinding
import com.project.albus.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_create_batch.*


class CreateFragment : Fragment() {

    lateinit var binding: FragmentCreateBatchBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // mainViewModel = ViewModelProvider(this).get()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_batch, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel=mainViewModel
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observer()
    }

    private fun observer() {
        mainViewModel.isSaved.observe(viewLifecycleOwner,{saved->
            if(saved){
                val action = CreateFragmentDirections.actionCreateFragmentToBatchSuccessFragment()
                findNavController().navigate(action)
            }
        })
    }

    private fun init() {

        createBtn.setOnClickListener {
            if(inputCode_text.text!=null){
                mainViewModel.saveModel()
            }else{
                inputCode_text.error="must be filled"
            }
        }
    }

}