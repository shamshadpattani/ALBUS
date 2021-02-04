package com.project.albus.ui.batch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.firebase.auth.FirebaseAuth
import com.project.albus.R
import com.project.albus.databinding.FragmentInviteBinding
import com.project.albus.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_invite.*


class InviteFragment : Fragment() {
    lateinit var binding: FragmentInviteBinding
    private var user = FirebaseAuth.getInstance()
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invite, container, false)
        viewModel = ViewModelProvider(this).get()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observer()
    }

    private fun observer() {
        viewModel.batchDetails.observe(viewLifecycleOwner, { batch ->
            if (batch != null) {
                goBtn.visibility = View.INVISIBLE
                batchCard.visibility = View.VISIBLE
                subjectName.text = batch.name
                ownerName.text = batch.owner
            }


        })
        viewModel.isMember.observe(viewLifecycleOwner, { isMember->
            if (isMember) {
                Toast.makeText(context, "Join Batch Successfully", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun init() {
        goBtn.setOnClickListener {
            if (inputInvite_text.text == null) {
                inputCode.error = "Code is required"
            } else {
                viewModel.getDetails(inputInvite_text.text.toString())
            }

        }
        joinBatchBtn.setOnClickListener {
            viewModel.updateMember(inputInvite_text.text.toString())
            requireActivity().onBackPressed()
        }
    }
}