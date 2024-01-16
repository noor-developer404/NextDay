package com.developer404.nestday.apis

import com.developer404.nestday.others.dataClass
import retrofit2.Call
import retrofit2.http.GET

interface api {
    @GET("api/users?page=2")
    fun getData():Call<dataClass>
}