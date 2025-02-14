package com.jetpack.master.downloadManager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager

class DownloadViewModel(application: Application): AndroidViewModel(application) {
    val liveWorkStatus = MediatorLiveData<WorkInfo>()

    fun doDownload() {
        val downloadWork = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .setInputData(
                Data.Builder()
                    .putString(
                        DownloadWorker.KEY_URL,
                        "https://commonsware.com/Android/source_1_0.zip"
                    )
                    .build()
            )
            .addTag("download")
            .build()

        val unZIPWork = OneTimeWorkRequest.Builder(UnzipWorker::class.java)
            .setConstraints(
                Constraints.Builder()
                    .setRequiresStorageNotLow(true)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .setInputData(
                Data.Builder()
                    .putString(DownloadWorker.KEY_RESULTDIR, "unzipped")
                    .build()
            )
            .addTag("unZip")
            .build()

        WorkManager.getInstance(getApplication())
            .beginWith(downloadWork)
            .then(unZIPWork)
            .enqueue()

        val liveOpStatus = WorkManager.getInstance(getApplication())
            .getWorkInfoByIdLiveData(unZIPWork.id)

        liveWorkStatus.addSource(liveOpStatus) {
            liveWorkStatus.value = it

            if (it != null) {
                if(it.state.isFinished) {
                    liveWorkStatus.removeSource(liveOpStatus)
                }
            }
        }
    }
}