package com.project.albus.ui.batch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.project.albus.R
import com.project.albus.databinding.FragmentCreateSchedulesBinding
import com.project.albus.databinding.FragmentInviteBinding
import com.project.albus.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_invite.*


class InviteFragment : Fragment() {
    lateinit var binding: FragmentInviteBinding
    private  val viewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invite, container, false)
        binding.viewmodel=viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observer()
    }

    private fun observer() {

    }

    private fun init() {
      //
            goBtn.setOnClickListener {
                if(inputInvite_text.text==null){
                    inputCode.error="Code is required"
                }else{
                    viewModel.getDetails(inputInvite_text.text.toString())
                }

            }
    }
}