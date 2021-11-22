package com.example.picnic_android_maryambehzi.search

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picnic_android_maryambehzi.network.GifModel
import com.example.picnic_android_maryambehzi.network.GiphyApi
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel : ViewModel() {

    private val _showRandomGif = MutableLiveData<Boolean>(true)
    val showRandomGif: LiveData<Boolean>
        get() = _showRandomGif

    private val _gif = MutableLiveData<GifModel>()
    val gif: LiveData<GifModel>
        get() = _gif

    private val _searchResult = MutableLiveData<List<GifModel>>()
    val searchResult: LiveData<List<GifModel>>
        get() = _searchResult

    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    private val _navigateToSelectedGif = MutableLiveData<GifModel>()
    val navigateToSelectedGif: LiveData<GifModel>
        get() = _navigateToSelectedGif


    init {
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                if (showRandomGif.value == true)
                    getRandomGif()
                mainHandler.postDelayed(this, 10000)
            }
        })
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

    private fun search(){
        viewModelScope.launch {
            try {
                _searchResult.value = query.value?.let { GiphyApi.retrofitService.searchQuery(query = it).data }
            }catch (e: Exception){
                print(e)
                _searchResult.value = emptyList()
            }
        }
    }

    fun textWatcherSearch(input: EditText) {
        input.doOnTextChanged { text, _, _, count ->
            _query.value = text.toString()
            if (count >= 2) {
                _showRandomGif.value = false
                search()
            }
            else{
                _showRandomGif.value = true
            }
        }
    }

    fun displayPropertyDetails(gifModel: GifModel) {
        _navigateToSelectedGif.value = gifModel
    }

    fun displayGifDetailsComplete() {
        _navigateToSelectedGif.value = null
    }
}