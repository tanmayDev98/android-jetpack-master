package com.jetpack.master.toDoSample.list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jetpack.master.databinding.RowTodoBinding
import com.jetpack.master.toDoSample.repository.ToDoModel

class ToDoViewHolder(private var binding: RowTodoBinding,
                     var onRowClick: (ToDoModel) -> Unit): ViewHolder(binding.root) {

    fun bind(model: ToDoModel) {
        binding.apply {
            tvTitle.text = model.title
            tvDescription.text = model.description
            root.setOnClickListener{onRowClick(model)}
        }
    }
}