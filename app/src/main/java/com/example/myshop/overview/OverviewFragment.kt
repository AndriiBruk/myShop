package com.example.myshop.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myshop.R
import com.example.myshop.databinding.FragmentOverviewBinding
import com.example.myshop.repositories.UserRepository


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater,container,false)
        binding.setLifecycleOwner (this)
        binding.viewModel = viewModel

//        viewModel.loginResponse.observe(this, Observer {
//            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
//        })
//        viewModel.loginResponse.observe(this, Observer {
//            UserRepository().catalogExport(it)
//        })
        return binding.root
    }


}