package com.jetpack.master.colorSample
import android.os.SystemClock
import androidx.lifecycle.LiveData
import java.util.*
import java.util.concurrent.Executors

class ColorLiveData: LiveData<List<Int>>() {
    private val random = Random()
    private val executor = Executors.newSingleThreadExecutor()

    override fun onActive() {
        super.onActive()

        if(value == null) {
            executor.execute {
                SystemClock.sleep(2000)
                postValue(List(25){random.nextInt()})
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
    }
}