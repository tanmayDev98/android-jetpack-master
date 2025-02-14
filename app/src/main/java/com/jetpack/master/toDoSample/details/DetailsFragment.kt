package com.jetpack.master.toDoSample.details

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jetpack.master.databinding.FragmentDetailsBinding

private const val ARG_MODEL_ID = "modelId"

class DetailsFragment(): Fragment() {
    private var binding: FragmentDetailsBinding? = null

    companion object {
        fun newInstance(modelId: String) = DetailsFragment().apply {
            arguments = bundleOf(ARG_MODEL_ID to modelId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDetailsBinding.inflate(layoutInflater, container,false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vm: DetailsViewModel by viewModels()
        val model = vm.getModel(arguments?.getString(ARG_MODEL_ID) ?: throw IllegalStateException("no modelId provided!"))

        model?.let {
            binding?.apply {
                modelBind = model
                createdOnFormatted = DateUtils.getRelativeDateTimeString(
                    activity,
                    model.createdOn.toEpochMilli(), DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.WEEK_IN_MILLIS, 0
                )
            }
        }
    }
}