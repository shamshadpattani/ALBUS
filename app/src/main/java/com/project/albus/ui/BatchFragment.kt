package com.project.albus.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.project.albus.R
import kotlinx.android.synthetic.main.fragment_batch.*


class BatchFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_batch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        joinBtn.setOnClickListener {
            val action = BatchFragmentDirections.actionBatchFragmentToInviteFragment()
            findNavController().navigate(action)
        }
        createBtn.setOnClickListener {
            val action = BatchFragmentDirections.actionBatchFragmentToCreateFragment()
            findNavController().navigate(action)
        }

    }

}