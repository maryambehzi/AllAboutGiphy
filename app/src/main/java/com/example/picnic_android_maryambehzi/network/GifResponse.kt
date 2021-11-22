package com.example.picnic_android_maryambehzi.network

import com.squareup.moshi.Json

data class GifResponse(
    @Json(name = ResponseKeys.DATA) val data : GifModel
)

object ResponseKeys{
    const val DATA = "data"
}
