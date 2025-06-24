package com.example.learnapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.learnapp.data.AppConstants
import com.example.learnapp.data.entity.LearnResponse
import com.example.learnapp.ui.repository.LearnRepository
import com.example.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(    private val learnRepository: LearnRepository
) :ViewModel() {
    private val _news : MutableStateFlow<ResourceState<LearnResponse>> = MutableStateFlow(ResourceState.Loading)
    val news: StateFlow<ResourceState<LearnResponse>> = _news //to ensure that cant be changede by UI

    init {
        getNews(AppConstants.COUNTRY)
    }

    private fun getNews(country:String) {
        viewModelScope.launch(Dispatchers.IO) {
            //IO thread
            learnRepository.getNewsHeadline(country)
                .collectLatest {
                    //extension functtion of flow, and this will give response
                    learnResponse ->

                    _news.value = learnResponse
                }

        }
        //learnRepository.getNewsHeadline() //suspended function can only be called from a corooutine
    }

    companion object {
        const val TAG = "LearnViewModel"
    }
}
