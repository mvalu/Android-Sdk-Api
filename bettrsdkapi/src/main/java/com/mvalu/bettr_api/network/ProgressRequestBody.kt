package com.mvalu.bettr_api.network

import android.os.Handler
import android.os.Looper
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

const val DEFAULT_BUFFER_SIZE = 2048

class ProgressRequestBody(
    private val contentType: String,
    private val file: File,
    private val apiTag: ApiTag,
    private val listener: DocumentUploadCallbacks
) :
    RequestBody() {

    interface DocumentUploadCallbacks {
        fun onProgressUpdate(percentage: Int, apiTag: ApiTag)
//        fun onError()
//        fun onFinish()
    }

    override fun contentType(): MediaType? {
        return MediaType.parse(contentType);
    }

    override fun contentLength(): Long {
        return file.length()
    }

    override fun writeTo(sink: BufferedSink) {
        val fileLength: Long = file.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val inputStream = FileInputStream(file)
        var uploaded: Long = 0

        inputStream.use { inputStream ->
            var read: Int
            val handler = Handler(Looper.getMainLooper())
            while (inputStream.read(buffer).also { read = it } != -1) {
                // update progress on UI thread
                handler.post(ProgressUpdater(uploaded, fileLength, apiTag, listener))
                uploaded += read.toLong()
                sink.write(buffer, 0, read)
            }
        }
    }

    private class ProgressUpdater(
        val uploaded: Long,
        val total: Long,
        val apiTag: ApiTag,
        val listener: DocumentUploadCallbacks
    ) : Runnable {
        override fun run() {
            listener.onProgressUpdate((100 * uploaded / total).toInt(), apiTag)
        }

    }
}