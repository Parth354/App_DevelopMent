package com.example.retrofitproject

import com.google.gson.annotations.SerializedName

data class AlbumItem (
    @SerializedName("id")
    val id:Int,
    @SerializedName("userId")//if we want to change the name of key
    val userId:Int,
    @SerializedName("title")
    val title:String)