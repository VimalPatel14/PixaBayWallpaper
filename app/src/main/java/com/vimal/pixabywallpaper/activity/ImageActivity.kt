package com.vimal.pixabywallpaper.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vimal.pixabywallpaper.databinding.ActivityImageBinding
import com.vimal.pixabywallpaper.utils.WallpaperHelper

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("image")
        binding.csSuccImg.visibility = View.GONE
        binding.csErrorImg.visibility = View.GONE
        binding.csLoadImg.visibility = View.VISIBLE
        Picasso.get()
            .load(url)
            .into(binding.imgSet, object : Callback {
                override fun onSuccess() {
                    binding.csSuccImg.visibility = View.VISIBLE
                    binding.csErrorImg.visibility = View.GONE
                    binding.csLoadImg.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.csSuccImg.visibility = View.GONE
                    binding.csErrorImg.visibility = View.VISIBLE
                    binding.csLoadImg.visibility = View.GONE
                }
            })

        binding.txtBad.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.txtHome.setOnClickListener {
            WallpaperHelper.setHomeWallpaper(this, url!!)
        }

        binding.txtLockHome.setOnClickListener {
            WallpaperHelper.setHomeLockWallpaper(this, url!!)
        }
    }
}