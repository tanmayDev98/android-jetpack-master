package com.jetpack.master.toDoSample.details

import androidx.lifecycle.ViewModel
import com.jetpack.master.toDoSample.repository.ToDoModel
import com.jetpack.master.toDoSample.repository.ToDoRepository

class DetailsViewModel: ViewModel() {
    private var model: ToDoModel? = null

    fun getModel(id: String) = model ?: ToDoRepository().findItemById(id).also {
        model = it
    }
}