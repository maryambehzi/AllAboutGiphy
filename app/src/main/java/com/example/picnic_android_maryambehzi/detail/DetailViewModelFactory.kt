package com.example.picnic_android_maryambehzi.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.picnic_android_maryambehzi.network.GifModel

class DetailViewModelFactory(
    private val gifModel: GifModel,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(gifModel, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}