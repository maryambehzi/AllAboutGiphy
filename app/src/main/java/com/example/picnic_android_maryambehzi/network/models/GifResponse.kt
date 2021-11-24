package com.example.picnic_android_maryambehzi.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GifResponse(
    @Json(name = ResponseKeys.DATA) val data : GifModel
) : Parcelable

object ResponseKeys{
    const val DATA = "data"
}
