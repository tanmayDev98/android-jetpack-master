package com.jetpack.master.lifeCycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jetpack.master.databinding.RowLifecycleBinding

class LifeCycleAdapter(private val layoutInflater: LayoutInflater, private val startTime: Long): ListAdapter<Event, LifeCycleViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifeCycleViewHolder =
        LifeCycleViewHolder(RowLifecycleBinding.inflate(layoutInflater, parent, false), startTime)

    override fun onBindViewHolder(holder: LifeCycleViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}

private object DiffCallBack: DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean
            = oldItem == newItem

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean
        = areItemsTheSame(oldItem, newItem)
}