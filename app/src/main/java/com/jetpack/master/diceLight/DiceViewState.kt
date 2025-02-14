package com.jetpack.master.diceLight

sealed class DiceViewState {
    object Loading : DiceViewState()
    data class Content(val passphrase: String, val wordCount: Int) : DiceViewState()
    data class Error(val throwable: Throwable) : DiceViewState()
}