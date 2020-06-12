package com.example.eldorado_test.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eldorado_test.data.dao.LogDao
import com.example.eldorado_test.data.dao.PermissionDao
import com.example.eldorado_test.data.entities.LogEntity
import com.example.eldorado_test.data.entities.PermissionEntity

@Database(entities = [PermissionEntity::class, LogEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun permissionDao(): PermissionDao
    abstract fun logDao(): LogDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}