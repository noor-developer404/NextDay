package com.developer404.nestday.others

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer404.nestday.FavList
import com.developer404.nestday.apis.api
import com.developer404.nestday.apis.retrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class mainViewModel: ViewModel() {
    val mutableLiveData = MutableLiveData<dataClass>()

    fun getData():MutableLiveData<dataClass>{
        return mutableLiveData
    }

    fun makeApiCall(){
        val apiInstance = retrofitInstance().getRetrofitInstance().create(api::class.java)
        val call: Call<dataClass> = apiInstance.getData()
        call.enqueue(object : Callback<dataClass>{
            override fun onResponse(call: Call<dataClass>, response: Response<dataClass>) {
                Log.e("call", "response.toString()" )
                mutableLiveData.postValue(response.body())

            }

            override fun onFailure(call: Call<dataClass>, t: Throwable) {
                Log.e("call", "Failed" )
            }

        })

    }
}