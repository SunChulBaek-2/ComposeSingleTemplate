package com.example.composetemplate.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class Photo(
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
)

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}