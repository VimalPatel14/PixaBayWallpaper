package com.vimal.pixabywallpaper.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vimal.pixabywallpaper.R
import com.vimal.pixabywallpaper.adapter.ImageAdapter
import com.vimal.pixabywallpaper.api.PixabyInterface
import com.vimal.pixabywallpaper.api.model.ImageModel
import com.vimal.pixabywallpaper.api.model.RequestModel
import com.vimal.pixabywallpaper.databinding.ActivityCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.csSucc.visibility=View.GONE
        binding.csError.visibility=View.GONE
        binding.csLoad.visibility=View.VISIBLE

        val imageList = ArrayList<ImageModel>()
        val recyclerView = findViewById<View>(R.id.rec_image) as RecyclerView
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.setLayoutManager(staggeredGridLayoutManager)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PixabyInterface::class.java)
        val call = service.getQuotes(intent.getStringExtra("category")!!)
        call.enqueue(object : Callback<RequestModel> {
            override fun onResponse(call: Call<RequestModel>, response: Response<RequestModel>) {
                if (response.code() == 200) {
                    binding.csSucc.visibility=View.VISIBLE
                    binding.csError.visibility=View.GONE
                    binding.csLoad.visibility=View.GONE
                    for (i in response.body()!!.hits){
                        imageList.add(i)
                    }
                    val adapter = ImageAdapter(this@CategoryActivity, imageList,intent.getStringExtra("ru_name")!!)
                    recyclerView.setAdapter(adapter)
                }
            }
            override fun onFailure(call: Call<RequestModel>, t: Throwable) {
                binding.csSucc.visibility=View.GONE
                binding.csError.visibility=View.VISIBLE
                binding.csLoad.visibility=View.GONE
                Log.e("t",t.toString())
            }
        })
    }
}