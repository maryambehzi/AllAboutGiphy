package com.example.picnic_android_maryambehzi

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picnic_android_maryambehzi.network.GifModel
import com.example.picnic_android_maryambehzi.utils.PhotoGridAdapter

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
        .placeholder(R.drawable.gif_placeholder_bg)
        .load(gifModel?.images?.fixedHeight?.url)
        .into(this)
}

@BindingAdapter("setPreviewGifImage")
fun ImageView.setPreviewGifImage(gifModel: GifModel?){
    Glide.with(context)
        .asGif()
        .placeholder(R.drawable.gif_placeholder_bg)
        .load(gifModel?.images?.previewGif?.url)
        .into(this)
}

@BindingAdapter("isRandomGifVisible")
fun ConstraintLayout.isRandomGifVisible(isShown : Boolean?){
    isShown?.let {
        visibility = if (it){
            View.VISIBLE
        } else{
            View.GONE
        }
    } ?: run { visibility = View.VISIBLE }
}

@BindingAdapter("isSearchedGifsVisible")
fun ConstraintLayout.isSearchedGifsVisible(isShown : Boolean?){
    isShown?.let {
        visibility = if (it){
            View.GONE
        } else{
            View.VISIBLE
        }
    } ?: run { visibility = View.GONE }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GifModel>?) {
    val adapter = recyclerView.adapter as? PhotoGridAdapter
    adapter?.submitList(data)
}

@BindingAdapter("setVisibility")
fun ImageView.setVisibility(isShown: Boolean){
     visibility = isShown.let {
        if (!it){
            View.VISIBLE
        }else{
            View.GONE
        }
    }
}

@BindingAdapter("setVisibility")
fun ImageButton.setVisibility(input: String?){
    visibility = input.let {
        if (it.isNullOrEmpty()){
            View.GONE
        }else{
            View.VISIBLE
        }
    }
}

@BindingAdapter("setVisibility")
fun ProgressBar.setVisibility(isShown: Boolean?){
    visibility = isShown?.let {
        if (it){
            View.VISIBLE
        }else{
            View.GONE
        }
    } ?: run {
        View.GONE
    }
}