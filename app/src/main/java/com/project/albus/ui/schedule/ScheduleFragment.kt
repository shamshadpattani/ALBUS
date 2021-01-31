package com.project.albus.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.albus.R
import com.project.albus.data.BatchDetails
import com.project.albus.databinding.FragmentScheduleBindingImpl
import com.project.albus.ui.batch.BatchQuickAdapter
import com.project.albus.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_create_batch.view.*
import kotlinx.android.synthetic.main.fragment_create_batch.view.createBtn
import kotlinx.android.synthetic.main.fragment_create_or_join.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : Fragment() {
    lateinit var binding: FragmentScheduleBindingImpl
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var mAdapter: BatchQuickAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        binding.lifecycleOwner = this
       // binding.viewmodel=mainViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initAdapter()
        observer()
    }

    private fun observer() {
      mainViewModel.getBatches().observe(viewLifecycleOwner,{
          if (it != null) {
             mAdapter.updateItems(it)
          }else{
          }
      })
    }

    private fun init() {
        toolbar.setOnMenuItemClickListener {
            if(it.itemId==R.id.menu_filter) {
                val action = ScheduleFragmentDirections.actionScheduleFragmentToCreateFragment()
                findNavController().navigate(action)
        }else if(it.itemId==R.id.menu_info){
                val action = ScheduleFragmentDirections.actionScheduleFragmentToInviteFragment()
                findNavController().navigate(action)
            }
            true
    }
    }

    private fun initAdapter() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(false)
        mAdapter = BatchQuickAdapter(mutableListOf())
        recyclerview.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val data= adapter.data[position] as BatchDetails
            val action =
                ScheduleFragmentDirections.actionScheduleFragmentToBatchDetailsFragment(data.code)
            findNavController().navigate(action)
        }
        val eds = layoutInflater.inflate(R.layout.fragment_create_or_join,recyclerview,false)
        mAdapter.setEmptyView(eds)
        eds.createBtn.setOnClickListener {
            val action = ScheduleFragmentDirections.actionScheduleFragmentToCreateFragment()
            findNavController().navigate(action)
        }
        eds.joinBtn.setOnClickListener {
            val action = ScheduleFragmentDirections.actionScheduleFragmentToInviteFragment()
            findNavController().navigate(action)
        }
    }
}