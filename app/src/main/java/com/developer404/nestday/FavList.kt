package com.developer404.nestday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer404.nestday.apis.fav_click
import com.developer404.nestday.databinding.ActivityFavListBinding
import com.developer404.nestday.others.adapter
import com.developer404.nestday.others.favAdapter
import com.developer404.nestday.others.mainViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class FavList : AppCompatActivity(),fav_click {
    lateinit var binding: ActivityFavListBinding
    lateinit var viewModel:mainViewModel
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityFavListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.favRv.layoutManager = LinearLayoutManager(this)
//        viewModel = ViewModelProvider(this).get(mainViewModel::class.java)
//        viewModel.makeApiCall()
//        binding.favRv.adapter = favAdapter(viewModel.mutableLiveData,this)

        binding.favRv.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(mainViewModel::class.java)
        viewModel.makeApiCall()
        binding.favRv.adapter = adapter(viewModel.mutableLiveData,this,this)

        viewModel.mutableLiveData.observe(this, Observer {
            binding.favRv.adapter = favAdapter(viewModel.mutableLiveData,this)
        })


    }

   }