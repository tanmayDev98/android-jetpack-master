package com.jetpack.master.downloadManager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException

class DownloadWorker(context: Context, parameters: WorkerParameters): Worker(context, parameters) {
    companion object {
        const val KEY_URL = "url"
        const val KEY_RESULTDIR = "resultDir"
    }

    override fun doWork(): Result {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(inputData.getString(KEY_URL)!!)
            .build()

        val dir = applicationContext.cacheDir
        val downloadedFile = File(dir, "temp.zip")

        if(downloadedFile.exists()) {
            downloadedFile.delete()
        }

        try {
            client.newCall(request).execute().use { response ->
                val sink = downloadedFile.sink().buffer()

                response.body?.let { sink.writeAll(it.source()) }
                sink.close()
            }
        } catch (e: IOException) {
            Log.e(javaClass.simpleName, "Exception downloading file", e)
            return Result.failure()
        }

        return Result.success(
            Data.Builder()
                .putString(UnzipWorker.KEY_ZIPFILE, downloadedFile.absolutePath)
                .build()
        )
    }
}