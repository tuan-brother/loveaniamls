package com.example.recycleviewsmooth.retrofit

class ApiUtils {
    constructor()

    companion object {
        val url: String = "https://dog.ceo/api/"
        fun getApi(): ApiService {
            return RetrofitClient.getRetrofit(url).create(ApiService::class.java)
        }
    }
}