package com.example.retrofitproject

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{//to create static instance
        val BASE_URL="https://jsonplaceholder.typicode.com"

        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

    }
}