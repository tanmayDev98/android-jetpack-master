package com.jetpack.master.diceKoin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.master.diceLight.DiceViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val DEFAULT_WORD_COUNT = 6;

class DiceMotorKoin(private val passphraseRepository: PassphraseRepositoryKoin): ViewModel() {
    //for live data
//    private val _results = MutableLiveData<DiceViewState>()
//    val results: LiveData<DiceViewState> = _results

    //for state flow
    private val _results = MutableStateFlow<DiceViewState>(DiceViewState.Loading)
    val results = _results.asStateFlow()

    init {
        generatePassphrase(DEFAULT_WORD_COUNT)
    }

    fun generatePassphrase() {
        generatePassphrase(
            (results.value as? DiceViewState.Content)?.wordCount ?: DEFAULT_WORD_COUNT
        )
    }

    fun generatePassphrase(wordCount: Int) {
        _results.value = DiceViewState.Loading
        viewModelScope.launch(Dispatchers.Main) {
            _results.value = try {
                val randomWords = passphraseRepository.generate(wordCount)
                DiceViewState.Content(randomWords.joinToString(" "), wordCount)
            } catch (t: Throwable) {
                DiceViewState.Error(t)
            }
        }
    }
}