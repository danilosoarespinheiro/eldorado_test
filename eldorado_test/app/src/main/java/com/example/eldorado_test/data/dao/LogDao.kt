package com.example.eldorado_test.data.dao

import androidx.room.*
import com.example.eldorado_test.data.entities.LogEntity
import com.example.eldorado_test.data.entities.PermissionEntity

@Dao
interface LogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLog(logEntity: LogEntity)

    @Query("SELECT * FROM LogEntity")
    fun getLog(): List<LogEntity>

}