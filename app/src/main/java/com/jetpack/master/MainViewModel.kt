package com.jetpack.master

import androidx.lifecycle.ViewModel
import com.jetpack.master.colorSample.ColorListActivity
import com.jetpack.master.contact.ContactsActivity
import com.jetpack.master.diceLight.DiceLightActivity
import com.jetpack.master.downloadManager.DownloadActivity
import com.jetpack.master.lifeCycle.LifeCycleActivity
import com.jetpack.master.toDoSample.ToDoActivity

class MainViewModel: ViewModel() {
    private val items = listOf(
        MainModel(1,"Color List", ColorListActivity()),
        MainModel(2,"Contact", ContactsActivity()),
        MainModel(3,"Dice Word Roll", DiceLightActivity()),
        MainModel(4,"Lifecycle and Events", LifeCycleActivity()),
        MainModel(5,"Mini To Do", ToDoActivity()),
        MainModel(6,"Dice Word Roll - Koin", DiceLightActivity()),
        MainModel(7,"Download Manager", DownloadActivity())
    ).associateBy { it.id }

    fun getitems() : List<MainModel> = items.values.toList()
}