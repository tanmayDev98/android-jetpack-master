package com.jetpack.master.toDoSample.list

import androidx.lifecycle.ViewModel
import com.jetpack.master.toDoSample.repository.ToDoRepository

class ListViewModel: ViewModel() {
    val items = ToDoRepository().getitems()
}