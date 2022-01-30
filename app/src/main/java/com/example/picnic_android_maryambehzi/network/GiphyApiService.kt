package com.example.picnic_android_maryambehzi.network

import com.example.picnic_android_maryambehzi.network.models.SearchResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.giphy.com/v1/gifs/"
private const val API_KEY = "ndkeFchWcYYGxxGJ4ukkLAg1LBQ5U3eC"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GiphyApiService {
    @GET("random")
    suspend fun getRandom(@Query(value = "api_key") apiKey :String = API_KEY) : GifResponse

    @GET("search")
    suspend fun searchQuery(@Query(value = "api_key") apiKey :String = API_KEY, @Query(value = "q") query :String = "") : SearchResponse
}

object GiphyApi {
    val retrofitService : GiphyApiService by lazy { retrofit.create(GiphyApiService::class.java) }
}