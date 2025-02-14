package com.jetpack.master.colorSample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.master.databinding.FragmentColorlistBinding

class ColorListFragment: Fragment() {

    private var binding: FragmentColorlistBinding? = null
    private val colorViewModel: ColorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModel already initialized using the property delegate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentColorlistBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ColorAdapter(layoutInflater).apply {
            submitList(colorViewModel.numbers)
        }

        //LiveData Changes
        //val adapter = ColorAdapter(layoutInflater)

        binding?.rvColorList?.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setAdapter(adapter)
        }

//Live Data Changes
//        colorViewModel.liveDataNumbers.observe(viewLifecycleOwner) { list ->
//            adapter.submitList(list)
//
//            binding?.let {
//                it.textView.visibility = if (list.isNotEmpty()) View.GONE else View.VISIBLE
//            }
//        }
    }

    //placed this in view model to avoid data changes due to configurations
    //private fun items() = List(25) { Random.nextInt() }
}