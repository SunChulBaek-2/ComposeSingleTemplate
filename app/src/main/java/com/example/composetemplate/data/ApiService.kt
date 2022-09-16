package com.example.composetemplate.data

import retrofit2.http.GET

class Photo

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}