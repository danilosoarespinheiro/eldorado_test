package com.example.eldorado_test

import android.app.Application
import android.content.Context
import com.example.eldorado_test.util.helper.PreferencesHelper

class App : Application(){
    private var context: Context? = null

    companion object{

        lateinit var preference: PreferencesHelper
    }


    override fun onCreate() {
        super.onCreate()
        context = this
        preference =
            PreferencesHelper(this)

    }
}
