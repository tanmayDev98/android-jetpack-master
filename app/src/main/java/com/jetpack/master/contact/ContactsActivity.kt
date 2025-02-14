package com.jetpack.master.contact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.jetpack.master.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {
    private val vm: ContactsViewModel by viewModels()
    private lateinit var binding: ActivityContactsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pick.setOnClickListener { pickContact.launch(null) }
        binding.view.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, vm.contact))
            } catch (e: Exception) {
                Toast.makeText(this, "Error viewing contact", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private val pickContact = registerForActivityResult(ActivityResultContracts.PickContact()) {
        vm.contact = it
        updateViewButton()
    }

    private fun updateViewButton() {
        if (vm.contact != null) {
            binding.view.isEnabled = true
        }
    }
}