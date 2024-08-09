package com.example.suitmediatest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatest.data.repository.UserRepository
import com.example.suitmediatest.data.response.DataItem

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    val user: LiveData<PagingData<DataItem>> =
        userRepository.getUser().cachedIn(viewModelScope)
}