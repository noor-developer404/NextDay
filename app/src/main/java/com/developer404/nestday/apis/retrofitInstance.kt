package com.developer404.nestday.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofitInstance {
    val baseUrl = "https://reqres.in/"
    var retrofit: Retrofit =getRetrofitInstance()

    fun getRetrofitInstance():Retrofit{
        if (retrofit==null){
            retrofit=Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}