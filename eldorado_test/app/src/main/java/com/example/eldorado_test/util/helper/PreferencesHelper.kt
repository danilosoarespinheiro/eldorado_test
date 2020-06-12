package com.example.eldorado_test.util.helper

import android.content.Context
import com.example.eldorado_test.R
import com.example.eldorado_test.data.Permission
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class PreferencesHelper (context : Context){

    private var preferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun savePermissions(permissions: ArrayList<Permission>?) {
        val edit = preferences?.edit()
        edit?.putString("permissions", Gson().toJson(permissions))
        edit?.apply()
    }

    fun getPermissions(): ArrayList<Permission>? {
        val gson = Gson()
        val json: String = preferences.getString("permissions", "").toString()
        val type: Type = object : TypeToken<ArrayList<Permission?>?>() {}.type
        return gson.fromJson(json, type)
    }
}