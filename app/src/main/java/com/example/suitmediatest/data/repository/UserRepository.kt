package com.example.suitmediatest.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmediatest.data.UserPagingSource
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.retrofit.ApiService
import com.example.suitmediatest.database.UserDatabase

class UserRepository(private val userDatabase: UserDatabase, private val apiService: ApiService) {
    fun getUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}