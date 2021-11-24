package com.example.picnic_android_maryambehzi.search

import android.os.Handler
import android.os.Looper
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

    private val _query = MutableLiveData<String?>()
    private val query: LiveData<String?>
        get() = _query

    private val _textInput = MutableLiveData<String?>()
    private val textInput: LiveData<String?>
        get() = _textInput

    private val _navigateToSelectedGif = MutableLiveData<GifModel>()
    val navigateToSelectedGif: LiveData<GifModel>
        get() = _navigateToSelectedGif

    private val _clearSearchBar = MutableLiveData<Boolean>(false)
    val clearSearchBar: LiveData<Boolean>
        get() = _clearSearchBar

    private val _openUrlLink = MutableLiveData<Boolean?>(false)
    val openUrlLink: LiveData<Boolean?>
        get() = _openUrlLink

    private val _onBackground = MutableLiveData<Boolean?>(false)
    val onBackground: LiveData<Boolean?>
        get() = _onBackground

    val mainHandler = Handler(Looper.getMainLooper())

    init {


        mainHandler.post(object : Runnable {
            override fun run() {
                if (showRandomGif.value == true  && onBackground.value == false ) {
                    getRandomGif()
                }
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

    fun search(){
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
        input.doOnTextChanged { text, _, _, _ ->
            _clearSearchBar.value = false
            text?.let {
                if (it.isNotEmpty()) {
                    _query.value = it.toString()
                    showSearchResults()
                    if (it.length >= 2 && it.toString() != textInput.value.toString()) {
                        _textInput.value = it.toString()
                        search()
                    }
                }
            }
        }
    }

    fun displayGifDetails(gifModel: GifModel) {
        _navigateToSelectedGif.value = gifModel
    }

    fun displayGifDetailsComplete() {
        _navigateToSelectedGif.value = null
    }

    fun onBackPressed(){
        _query.value = null
        _textInput.value = null
        showRandomGif()
        _clearSearchBar.value = true
    }

    fun showSearchResults(){
        _showRandomGif.value = false
    }

    private fun showRandomGif(){
        _showRandomGif.value = true
    }

    fun openLinkInBrowser(){
        _openUrlLink.value = true
    }

    fun linkHasBeenShowed(){
        _openUrlLink.value = false
    }

    fun appPaused(){
        _onBackground.value = true
    }

    fun appResumed(){
        _onBackground.value = false
    }

    fun stopHandler(){
    }
}