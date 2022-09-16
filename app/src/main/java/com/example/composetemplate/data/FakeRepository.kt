package com.example.composetemplate.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeRepository @Inject constructor(
    private val apiService: ApiService
) {

}