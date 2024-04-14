package com.example.retrofitproject

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {

    //specify the endPoints
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums?")
    suspend fun getSpecificAlbums(@Query("userId") userId:Int) :Response<Albums>//first mention key then value
}