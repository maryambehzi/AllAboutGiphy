package com.example.picnic_android_maryambehzi.network.models

import android.os.Parcelable
import com.example.picnic_android_maryambehzi.network.GifModel
import com.example.picnic_android_maryambehzi.network.ResponseKeys
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    @Json(name = ResponseKeys.DATA) val data : List<GifModel>
) : Parcelable
