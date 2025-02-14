package com.jetpack.master.lifeCycle

import android.os.Parcelable
import android.os.SystemClock
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val message: String,
    val activityHash: Int,
    val viewmodelHash: Int,
    val timestamp: Long = SystemClock.elapsedRealtime()
): Parcelable
