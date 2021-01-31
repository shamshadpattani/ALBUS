package com.project.albus.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.project.albus.R
import com.project.albus.databinding.FragmentBatchDetailsBindingImpl
import kotlinx.android.synthetic.main.fragment_batch_details.*


class BatchDetailsFragment : Fragment() {
    lateinit var binding: FragmentBatchDetailsBindingImpl
   private val args: BatchDetailsFragmentArgs by navArgs()
   private  val viewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_batch_details, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initAdapter()
        observe()
    }

    private fun initAdapter() {

    }

    private fun observe() {
        viewModel.batchDetails.observe(viewLifecycleOwner,{batch->
            toolbar.title=batch?.name

        })
    }

    private fun init() {

        viewModel.getDetails(args.code)
    }
}