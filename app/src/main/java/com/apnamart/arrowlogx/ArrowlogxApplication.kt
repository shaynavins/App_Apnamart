package com.apnamart.arrowlogx

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArrowlogxApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "inside_onCreate")
    }

    companion object{
        const val TAG = "ArrowlogxApplication"
    }

}