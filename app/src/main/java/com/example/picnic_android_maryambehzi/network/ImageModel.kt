package com.example.picnic_android_maryambehzi.network

import com.squareup.moshi.Json

data class ImageModel(
    @Json(name = ImagesKeys.ORIGINAL) val original : OriginalModel,
    @Json(name = ImagesKeys.FIXED_HEIGHT) val fixedHeight : OriginalModel,
    @Json(name = ImagesKeys.PREVIEW_GIF) val previewGif : OriginalModel
)

object ImagesKeys{
    const val ORIGINAL = "original"
    const val FIXED_HEIGHT = "fixed_height"
    const val PREVIEW_GIF = "preview_gif"
}
