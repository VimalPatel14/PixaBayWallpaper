package com.vimal.pixabywallpaper.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vimal.pixabywallpaper.R
import com.vimal.pixabywallpaper.adapter.CategoryAdapter
import com.vimal.pixabywallpaper.model.CategoryModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.rec_category)

        recyclerview.layoutManager = GridLayoutManager(this,2,)
        val categoryList = ArrayList<CategoryModel>()
        categoryList.add(CategoryModel("Lightning MCQUEEN","car","https://img1.wallspic.com/crops/4/9/0/7/6/167094/167094-rolls_royce-rolls_royce_wraith-cars-rolls_royce_armoured_car-rolls_royce_phantom-1080x1920.jpg"))
        categoryList.add(CategoryModel("Nature","nature","https://img1.wallspic.com/crops/8/1/3/4/0/104318/104318-forest-tianmen_mountain-vegetation-ecosystem-spruce_fir_forest-1080x1920.jpg"))
        categoryList.add(CategoryModel("kittens","animals","https://img2.wallspic.com/crops/6/8/2/6/96286/96286-terrestrial_animal-jaguar-television-leopard-cheetah-1080x1920.jpg"))
        categoryList.add(CategoryModel("Blue(color)","blue","https://img2.wallspic.com/crops/4/6/1/6/6/166164/166164-iphone_13_pro_official_stock_wallpaper_sierra_blue_light-1080x1920.jpg"))
        categoryList.add(CategoryModel("-_-","minimalism","https://img2.wallspic.com/previews/0/0/7/0/7/170700/170700-illustration-art-flat_design-minimalism-atmosphere-x750.jpg"))
        categoryList.add(CategoryModel("Food","food","https://img3.wallspic.com/previews/2/2/6/5/4/145622/145622-green_and_white_sliced_citrus_fruits-x750.jpg"))
        categoryList.add(CategoryModel("healthy lifestyle","sport","https://img2.wallspic.com/previews/7/7/4/0/5/150477/150477-person_in_black_pants_and_red_and_white_soccer_ball-x750.jpg"))
        categoryList.add(CategoryModel("shell","sky","https://img1.wallspic.com/previews/9/6/9/4/3/134969/134969-red-sunrise-morning-sky-sunset-x750.jpg"))

        // This will pass the ArrayList to our Adapter
        val adapter = CategoryAdapter(categoryList,this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}