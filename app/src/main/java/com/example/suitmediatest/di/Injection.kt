package com.example.suitmediatest.di

import android.content.Context
import com.example.suitmediatest.data.repository.UserRepository
import com.example.suitmediatest.data.retrofit.ApiConfig
import com.example.suitmediatest.database.UserDatabase

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository(database, apiService)
    }
}