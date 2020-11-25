package com.mvalu.bettr_api.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

object ApiSdkFileUtils {

    fun getMimeType(fileUri: Uri, context: Context): String? {
        return if (ContentResolver.SCHEME_CONTENT.equals(fileUri.getScheme())) {
            val cr: ContentResolver = context?.getContentResolver()!!
            cr.getType(fileUri)
        } else {
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                MimeTypeMap.getFileExtensionFromUrl(
                    fileUri
                        .toString()
                ).toLowerCase()
            )
        }
    }
}