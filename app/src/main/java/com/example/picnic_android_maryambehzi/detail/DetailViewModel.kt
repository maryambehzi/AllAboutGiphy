package com.example.picnic_android_maryambehzi.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.picnic_android_maryambehzi.network.GifModel

class DetailViewModel(gifModel: GifModel, app: Application) : AndroidViewModel(app)   {
    private val _selectedGif = MutableLiveData<GifModel>()
    val selectedGif: LiveData<GifModel>
        get() = _selectedGif

    private val _backIsPresses = MutableLiveData<Boolean>(false)
    val backIsPresses: LiveData<Boolean>
        get() = _backIsPresses

    private val _urlLink = MutableLiveData<String?>()
    val urlLink: LiveData<String?>
        get() = _urlLink

    init {
        _selectedGif.value = gifModel
    }

    fun onBackPressed(){
        _backIsPresses.value = true
    }

    fun openLinkInBrowser(){
        _urlLink.value = selectedGif.value?.url
    }
}