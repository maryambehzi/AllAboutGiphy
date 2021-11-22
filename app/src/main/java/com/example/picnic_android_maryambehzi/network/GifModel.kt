package com.example.picnic_android_maryambehzi.network

import com.squareup.moshi.Json

data class GifModel(
    @Json(name = Keys.ID) val id : String,
    @Json(name = Keys.URL) val url : String,
    @Json(name = Keys.TITLE) val title : String,
    @Json(name = Keys.RATING) val rating : String
)

object Keys {
    const val ID = "id"
    const val URL = "url"
    const val TITLE = "title"
    const val RATING = "rating"
}
