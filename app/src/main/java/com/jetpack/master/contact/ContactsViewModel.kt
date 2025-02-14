package com.jetpack.master.contact

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val STATE_CONTACT = "contact"

class ContactsViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
    var contact: Uri? = stateHandle[STATE_CONTACT]
        set(value) {
            field = value
            stateHandle[STATE_CONTACT] = value
        }
}