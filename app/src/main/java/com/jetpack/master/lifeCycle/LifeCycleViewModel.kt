package com.jetpack.master.lifeCycle

import android.os.SystemClock
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val STATE_EVENTS = "events"
private const val STATE_START_TIME = "startTime"

class LifeCycleViewModel(private val savedState: SavedStateHandle): ViewModel() {
//    val events: MutableList<Event> = mutableListOf()
//    val startTime = SystemClock.elapsedRealtime()
    private val id = Random.nextInt()
    val events: ArrayList<Event> = savedState[STATE_EVENTS] ?: arrayListOf()
    val startTime: Long = savedState[STATE_START_TIME] ?:
        SystemClock.elapsedRealtime().also { savedState[STATE_START_TIME] = it }

    fun addEvent(message: String, activityHash: Int) {
        //Id is viewmodelHash
        events.add(Event(message, activityHash, id))
        savedState[STATE_EVENTS] = events
    }

    override fun onCleared() {
        events.clear()
    }
}