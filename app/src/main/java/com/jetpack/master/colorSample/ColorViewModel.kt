package com.jetpack.master.colorSample

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val STATE_NUMBERS = "numbers"

class ColorViewModel(private val savedState: SavedStateHandle): ViewModel() {
    private val random = Random
    var numbers: List<Int> = savedState[STATE_NUMBERS] ?: buildItems()

    //var liveDataNumbers = ColorLiveData()

    fun refresh() {
        numbers = buildItems()
        savedState[STATE_NUMBERS] = numbers
    }

    private fun buildItems() = List(25) { random.nextInt() }

    override fun onCleared() {
        Log.d("ViewModel", "onCleared")
        super.onCleared()
    }
}