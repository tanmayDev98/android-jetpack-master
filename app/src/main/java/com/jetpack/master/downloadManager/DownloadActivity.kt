package com.jetpack.master.downloadManager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.work.WorkInfo
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.databinding.BindingAdapter
import com.jetpack.master.databinding.ActivityDownloadBinding

@BindingAdapter("android:enabled")
fun View.setEnabled(info: WorkInfo?) {
    isEnabled = info?.state?.isFinished ?: true
}

class DownloadActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm:DownloadViewModel by viewModels()
        val binding = ActivityDownloadBinding.inflate(layoutInflater)

        binding.viewModel = vm
        binding.lifecycleOwner = this

        setContentView(binding.root)

        vm.liveWorkStatus.observe(this) {workStatus ->
            if (workStatus != null && workStatus.state.isFinished) {
                Toast.makeText(this, "Done!", Toast.LENGTH_LONG).show()
            }
        }
    }
}