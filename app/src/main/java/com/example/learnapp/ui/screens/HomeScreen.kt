package com.example.learnapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnapp.ui.components.Loader
import com.example.learnapp.ui.components.NewsList
import com.example.learnapp.ui.viewmodel.LearnViewModel
import com.example.utilities.ResourceState

const val TAG = "HomeScreen"
@Composable
fun HomeScreen(

    learnViewModel: LearnViewModel = hiltViewModel()
) {

    val newsResponse by learnViewModel.news.collectAsState() //collect updated news in composable

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        when (newsResponse) {
            is ResourceState.Loading -> {
                Log.d(TAG, "Inside_loading")
                Loader()

            }
            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success).data
                NewsList(response)
                Log.d(TAG, "Inside_success")

            }
            is ResourceState.Error -> {
                val error = (newsResponse as ResourceState.Error)

                Log.d(TAG, "Inside_error $error")


            }
        }

    }
}



@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}