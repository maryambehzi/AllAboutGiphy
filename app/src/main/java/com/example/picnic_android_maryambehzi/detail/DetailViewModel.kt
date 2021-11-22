package com.example.picnic_android_maryambehzi.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.picnic_android_maryambehzi.network.GifModel

class DetailViewModel(gifModel: GifModel, app: Application) : AndroidViewModel(app)   {
    private val _selectedProperty = MutableLiveData<GifModel>()
    val selectedProperty: LiveData<GifModel>
        get() = _selectedProperty

    init {
        _selectedProperty.value = gifModel
    }
}