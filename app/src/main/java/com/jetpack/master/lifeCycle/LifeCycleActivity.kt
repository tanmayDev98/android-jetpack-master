package com.jetpack.master.lifeCycle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.master.databinding.ActivityListBinding
import kotlin.random.Random

private const val TAG = "LifeCycle-Tag"

class LifeCycleActivity: AppCompatActivity() {

    private val vm :LifeCycleViewModel by viewModels()
    private lateinit var lifeCycleAdapter: LifeCycleAdapter
    private val id = Random.nextInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifeCycleAdapter = LifeCycleAdapter(layoutInflater, vm.startTime)
        addEvent("onCreate()")

        //Commented to test live data on colorsList via fragment
        binding.rvColorList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this@LifeCycleActivity, DividerItemDecoration.VERTICAL))
            setAdapter(lifeCycleAdapter)
        }
    }

    override fun onStart() {
        super.onStart()
        addEvent("onStart()")
    }
    override fun onResume() {
        super.onResume()
        addEvent("onResume()")
    }
    override fun onPause() {
        super.onPause()
        addEvent("onPause()")
    }
    override fun onStop() {
        super.onStop()
        addEvent("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        addEvent("onDestroy()")
    }

    private fun addEvent(message: String) {
        vm.addEvent(message, id)
        lifeCycleAdapter.submitList(ArrayList(vm.events))
    }
}