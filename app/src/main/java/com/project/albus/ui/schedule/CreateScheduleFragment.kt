package com.project.albus.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.project.albus.R
import com.project.albus.databinding.FragmentCreateSchedulesBinding
import com.project.albus.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_create_batch.*


class CreateScheduleFragment : Fragment() {
    var user = FirebaseAuth.getInstance().currentUser?.uid
    lateinit var binding: FragmentCreateSchedulesBinding
   private val args: CreateScheduleFragmentArgs by navArgs()
    private lateinit var mAdapter: ScheduleItemQuickAdapter
   private  val viewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_schedules, container, false)
        binding.viewmodel=viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
       createBtn.setOnClickListener {
           viewModel.updateSchedules(args.code)
       }
    }
}