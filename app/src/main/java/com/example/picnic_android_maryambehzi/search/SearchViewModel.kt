package com.example.picnic_android_maryambehzi.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picnic_android_maryambehzi.network.GifModel
import com.example.picnic_android_maryambehzi.network.GiphyApi
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel : ViewModel() {

    private val _gif = MutableLiveData<GifModel>()
    val gif: LiveData<GifModel>
        get() = _gif

    init {
        getRandomGif()
    }

    private fun getRandomGif(){
        viewModelScope.launch {
            try {
                _gif.value =GiphyApi.retrofitService.getRandom().data
            }catch (e: Exception){
                print(e)

            }
        }
    }
}