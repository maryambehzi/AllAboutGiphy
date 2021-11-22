package com.example.picnic_android_maryambehzi

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import com.bumptech.glide.Glide
import com.example.picnic_android_maryambehzi.network.GifModel

@BindingAdapter("setGifTitle")
fun TextView.setGifTitle(gifModel: GifModel?) {
    text = gifModel?.title
}

@BindingAdapter("setGifUrl")
fun TextView.setGifUrl(gifModel: GifModel?) {
    text = gifModel?.url
}

@BindingAdapter("setGifRating")
fun TextView.setGifRating(gifModel: GifModel?) {
    gifModel?.let {
        visibility = View.VISIBLE
        text = resources.getString(R.string.age_restriction_text, when(it.rating){
            "g" -> 1
            "pg" -> 8
            "pg-13"-> 13
            "r"-> 18
            else ->18
        })
    }?: run{
        visibility = View.GONE
    }
}

@BindingAdapter("setGifImage")
fun ImageView.setGifImage(gifModel: GifModel?){
    Glide.with(context)
        .asGif()
        .load(gifModel?.images?.fixedHeight?.url)
        .into(this)
}

@BindingAdapter("isRandomGifVisible")
fun ConstraintLayout.isRandomGifVisible(query : String?){
    query?.let {
        visibility = if (query.isNullOrEmpty()){
            View.VISIBLE
        } else{
            View.GONE
        }
    } ?: run { visibility = View.VISIBLE }
}