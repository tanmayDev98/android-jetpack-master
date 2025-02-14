package com.jetpack.master.lifeCycle

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jetpack.master.databinding.RowLifecycleBinding

class LifeCycleViewHolder(private val binding: RowLifecycleBinding, private val startTime: Long): ViewHolder(binding.root) {
    fun bindTo(event: Event) {
        //For data binding
        binding.elapsedTime = (event.timestamp - startTime) / 1000
        binding.event = event
        binding.executePendingBindings()

        //With ViewBinding
//        binding.apply {
//            val elapsedSeconds = (event.timestamp - startTime) / 1000
//            timestamp.text = DateUtils.formatElapsedTime(elapsedSeconds)
//            message.text = event.message
//            activityHash.text = Integer.toHexString(event.activityHash)
//            viewmodelHash.text = Integer.toHexString(event.viewmodelHash)
//        }
    }
}