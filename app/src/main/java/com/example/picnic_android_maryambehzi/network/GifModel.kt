package com.example.picnic_android_maryambehzi.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GifModel(
    @Json(name = Keys.ID) val id : String,
    @Json(name = Keys.URL) val url : String,
    @Json(name = Keys.TITLE) val title : String,
    @Json(name = Keys.RATING) val rating : String,
    @Json(name = Keys.IMAGES) val images : @RawValue ImageModel
): Parcelable{

}

object Keys {
    const val ID = "id"
    const val URL = "url"
    const val TITLE = "title"
    const val RATING = "rating"
    const val IMAGES = "images"
}
