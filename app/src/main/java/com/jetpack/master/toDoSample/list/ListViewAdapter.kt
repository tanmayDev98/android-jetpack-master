package com.jetpack.master.toDoSample.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jetpack.master.databinding.RowTodoBinding
import com.jetpack.master.toDoSample.repository.ToDoModel

class ListViewAdapter(private val inflater: LayoutInflater, private val onRowClick: (ToDoModel) -> Unit): ListAdapter<ToDoModel, ToDoViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder =
        ToDoViewHolder(RowTodoBinding.inflate(inflater,parent, false), onRowClick)

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) =
        holder.bind(getItem(position))

}

private object DiffCallBack: DiffUtil.ItemCallback<ToDoModel>() {
    override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean =
        areItemsTheSame(oldItem, newItem)
}