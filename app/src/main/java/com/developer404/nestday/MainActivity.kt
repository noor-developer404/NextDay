package com.developer404.nestday

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer404.nestday.apis.fav_click
import com.developer404.nestday.databinding.ActivityMainBinding
import com.developer404.nestday.others.adapter
import com.developer404.nestday.others.mainViewModel

class MainActivity : AppCompatActivity(),fav_click {
    lateinit var viewModel : mainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRv.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(mainViewModel::class.java)
        viewModel.makeApiCall()
        binding.mainRv.adapter = adapter(viewModel.mutableLiveData,this,this)

        viewModel.mutableLiveData.observe(this, Observer {
            binding.mainRv.adapter = adapter(viewModel.mutableLiveData,this,this)
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_fav -> {
//                Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,FavList::class.java)
//                intent.putExtra("data",viewModel.mutableLiveData)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun clicked(position: Int, view: View) {

        val sp = getSharedPreferences("fav", MODE_PRIVATE)
        val sp_editor = sp.edit()
        if (sp.getString(position.toString(),"")==""){
            Toast.makeText(this,"hai",Toast.LENGTH_SHORT).show()
            sp_editor.putString(position.toString(),position.toString())
            sp_editor.apply()
            view.findViewById<ImageView>(R.id.item_fav).setImageResource(R.drawable.ic_red_fav)
        }else{
            Toast.makeText(this,"nahi hai",Toast.LENGTH_SHORT).show()
            sp_editor.remove(position.toString())
            sp_editor.apply()
            view.findViewById<ImageView>(R.id.item_fav).setImageResource(R.drawable.ic_fav_outline)
        }


        super.clicked(position,view)
    }
}