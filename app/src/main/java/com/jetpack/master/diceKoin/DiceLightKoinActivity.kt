package com.jetpack.master.diceKoin

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jetpack.master.R
import com.jetpack.master.databinding.ActivityDiceLightBinding
import com.jetpack.master.diceLight.DiceViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiceLightKoinActivity: AppCompatActivity() {
    //using Koin's viewmodel instead of jetpack's viewmodel to
    //delegate
    private val motor: DiceMotorKoin by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDiceLightBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                motor.results.collect() { viewState ->
                    when (viewState) {
                        is DiceViewState.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                            binding.passphrase.text = ""
                        }

                        is DiceViewState.Content -> {
                            binding.progress.visibility = View.GONE
                            binding.passphrase.text = viewState.passphrase
                        }

                        is DiceViewState.Error -> {
                            binding.progress.visibility = View.GONE
                            binding.passphrase.text = viewState.throwable.localizedMessage
                            Log.e(
                                "Diceware",
                                "Exception generating passphrase",
                                viewState.throwable
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dice_light, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                motor.generatePassphrase()
                return true
            }

            R.id.word_count_4, R.id.word_count_5, R.id.word_count_6, R.id.word_count_7,
            R.id.word_count_8, R.id.word_count_9, R.id.word_count_10 -> {
                item.isChecked = !item.isChecked

                motor.generatePassphrase(Integer.parseInt(item.title.toString()))

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}