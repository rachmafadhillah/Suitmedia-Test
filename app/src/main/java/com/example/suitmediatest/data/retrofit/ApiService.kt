package com.example.suitmediatest.data.retrofit

import com.example.suitmediatest.data.response.UsernameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): UsernameResponse
}