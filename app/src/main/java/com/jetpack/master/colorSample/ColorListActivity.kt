package com.jetpack.master.colorSample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.master.R
import com.jetpack.master.databinding.ActivityListBinding

private const val TAG = "ViewModel"

class ColorListActivity: AppCompatActivity() {
    private val colorViewModel: ColorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //This helps to set toolbar as action bar: - Title is inherited from android manifest
        //Need to override onCreateOptionsMenu and onOptionsItemSelected
        //setSupportActionBar(binding.toolbar)

        var colorAdapter = ColorAdapter(layoutInflater)

        binding.toolbar.apply {
            setTitle(R.string.app_name)
            inflateMenu(R.menu.menu)
            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.refresh -> {
                        colorViewModel.refresh()
                        colorAdapter.apply {
                            submitList(colorViewModel.numbers)
                        }
                        true
                    }
                    R.id.about -> {
                        Toast.makeText(this@ColorListActivity, R.string.menu_about, Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }
        }
         //To set the view as a fragment
//        if(savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().replace(R.id.fragmentColorView, ColorListFragment()).commit()
//        }

        binding?.rvColorList?.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    this@ColorListActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = colorAdapter.apply { submitList(colorViewModel.numbers) }
        }
        Log.d(TAG, "onCreate() called!")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called!")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called!")
    }

    override fun onPause() {
        //Called in configuration changes
        Log.d(TAG, "onPause() called!")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop() called!")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() called!")
        super.onDestroy()
    }
}