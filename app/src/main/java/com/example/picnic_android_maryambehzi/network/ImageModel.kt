package com.example.picnic_android_maryambehzi.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ImageModel(
    @Json(name = ImagesKeys.ORIGINAL) val original : @RawValue OriginalModel,
    @Json(name = ImagesKeys.FIXED_HEIGHT) val fixedHeight : @RawValue OriginalModel,
    @Json(name = ImagesKeys.PREVIEW_GIF) val previewGif :  @RawValue OriginalModel
) : Parcelable

object ImagesKeys{
    const val ORIGINAL = "original"
    const val FIXED_HEIGHT = "fixed_height"
    const val PREVIEW_GIF = "preview_gif"
}
