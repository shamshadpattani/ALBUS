package com.project.albus.ui.batch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.albus.R
import com.project.albus.databinding.FragmentBatchSuccessBindingImpl
import com.project.albus.ui.MainViewModel
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
        init()
        observe()
    }

    private fun init() {
        share_code_btn.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Join Code is:${mainViewModel.code.value}")
            sendIntent.type = "text/plain"
            Intent.createChooser(sendIntent, "Share via")
            startActivity(sendIntent)
        }
        done_btn.setOnClickListener {
            val action = BatchSuccessFragmentDirections.actionBatchSuccessFragmentToScheduleFragment()
            findNavController().navigate(action)
        }
    }

    private fun observe() {

    }
}