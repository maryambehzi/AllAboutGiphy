package com.example.picnic_android_maryambehzi.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class OriginalModel(
    @Json(name = OriginalKeys.URL) val url : @RawValue String
) : Parcelable

object OriginalKeys{
    const val URL = "url"
}