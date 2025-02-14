package com.jetpack.master.downloadManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class UnzipWorker(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {
    override fun doWork(): Result {
        val downloadedFile = File(inputData.getString(KEY_ZIPFILE)!!)
        val dir = applicationContext.cacheDir
        val resultDirData = inputData.getString(KEY_RESULTDIR)
        val resulDir = File(dir, resultDirData ?: "resutls")

        try {
           unzip(downloadedFile, resulDir)
            downloadedFile.delete()
        } catch (e:Exception) {
            Log.e(javaClass.simpleName, "Exception unZIPing file", e)
            return Result.failure()
        }

        return Result.success()
    }

    private fun unzip(zipFile: File, outputDir: File) {
        if (!outputDir.exists()) outputDir.mkdirs()

        val buffer = ByteArray(1024)

        ZipInputStream(FileInputStream(zipFile)).use { zipInputStream ->
            var zipEntry: ZipEntry? = zipInputStream.nextEntry

            while (zipEntry != null) {
                val file = File(outputDir, zipEntry.name)

                if (zipEntry.isDirectory) {
                    file.mkdirs()
                } else {
                    file.parentFile?.mkdirs()
                    FileOutputStream(file).use { fileOutputStream ->
                        var length: Int
                        while (zipInputStream.read(buffer).also { length = it } > 0) {
                            fileOutputStream.write(buffer, 0, length)
                        }
                    }
                }
                zipEntry = zipInputStream.nextEntry
            }
            zipInputStream.closeEntry()
        }
    }

    companion object {
        const val KEY_ZIPFILE = "zipFile"
        const val KEY_RESULTDIR = "resultDir"
    }
}