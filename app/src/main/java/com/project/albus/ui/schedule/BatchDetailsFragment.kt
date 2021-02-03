package com.project.albus.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.project.albus.R
import com.project.albus.databinding.FragmentBatchDetailsBindingImpl
import com.project.albus.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_batch_details.*
import kotlinx.android.synthetic.main.fragment_batch_details.recyclerview
import kotlinx.android.synthetic.main.fragment_batch_details.toolbar
import kotlinx.android.synthetic.main.fragment_schedule.*


class BatchDetailsFragment : Fragment() {

    lateinit var binding: FragmentBatchDetailsBindingImpl
    private val args: BatchDetailsFragmentArgs by navArgs()
    private lateinit var mAdapter: ScheduleItemQuickAdapter
    private val viewModel: MainViewModel by activityViewModels()

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
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(false)
        mAdapter = ScheduleItemQuickAdapter(mutableListOf())
        recyclerview.adapter = mAdapter
    }

    private fun observe() {
        viewModel.batchDetails.observe(viewLifecycleOwner, { batch ->
            toolbar.title = batch?.name
            if (!batch?.schedules.isNullOrEmpty()) {
                nothingScheduledView.visibility = View.GONE
                mAdapter.updateItems(batch?.schedules!!)
            } else {
                var user = FirebaseAuth.getInstance().currentUser?.uid
                nothingScheduledView.visibility = View.VISIBLE
                if (user == batch?.owner) {
                    scheduleCreateBtn.visibility = View.VISIBLE
                    scheduleCreateBtn.setOnClickListener {
                        val action = BatchDetailsFragmentDirections.actionBatchDetailsFragmentToCreateScheduleFragment(args.code)
                        findNavController().navigate(action)
                    }
                } else {
                    scheduleCreateBtn.visibility = View.GONE
                }
            }
        })
    }

    private fun init() {
        viewModel.getDetails(args.code)

        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_info) {
                val action =  BatchDetailsFragmentDirections.actionBatchDetailsFragmentToCreateScheduleFragment(args.code)
                findNavController().navigate(action)
            }
            true
        }
    }
}