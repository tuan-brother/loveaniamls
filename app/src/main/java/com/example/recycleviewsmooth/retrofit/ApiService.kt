package com.example.recycleviewsmooth.retrofit

import com.example.recycleviewsmooth.model.Respone
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("breeds/image/random/")
    fun getData(
    ): Call<Respone?>?
}