package com.ubaya.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.studentapp.R
import com.ubaya.studentapp.databinding.FragmentPlaneListBinding
import com.ubaya.studentapp.viewmodel.PlaneViewModel

class PlaneListFragment : Fragment() {
    private lateinit var binding: FragmentPlaneListBinding
    private lateinit var planeViewModel: PlaneViewModel
    private val planeListAdapter = PlaneListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaneListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planeViewModel = ViewModelProvider(this).get(PlaneViewModel::class.java)
        planeViewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = planeListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        planeViewModel.planeLD.observe(viewLifecycleOwner, Observer {
            planeListAdapter.updatePlaneList(it)
        })
    }
}