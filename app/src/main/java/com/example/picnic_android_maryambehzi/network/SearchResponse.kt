package com.example.picnic_android_maryambehzi.network

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = ResponseKeys.DATA) val data : List<GifModel>
)
