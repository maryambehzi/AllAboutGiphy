package com.example.picnic_android_maryambehzi.network

import com.squareup.moshi.Json

data class OriginalModel(
    @Json(name = OriginalKeys.URL) val url : String
)

object OriginalKeys{
    const val URL = "url"
}