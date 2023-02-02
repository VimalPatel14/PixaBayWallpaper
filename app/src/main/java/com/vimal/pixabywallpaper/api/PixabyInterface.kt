package com.vimal.pixabywallpaper.api

import com.vimal.pixabywallpaper.api.model.RequestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabyInterface {
    @GET("?key=33106230-b104905cd7ff74ed17e2229af&")
     fun getQuotes(@Query("q") category: String) : Call<RequestModel>
}