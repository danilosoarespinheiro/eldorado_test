package com.example.eldorado_test.data.dao

import androidx.room.*
import com.example.eldorado_test.data.entities.PermissionEntity

@Dao
interface PermissionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPermission(permissionEntity: PermissionEntity)

    @Update
    fun updatePermission(permissionEntity: PermissionEntity)

    @Delete
    fun deletePermission(permissionEntity: PermissionEntity)

    @Query("SELECT * FROM PermissionEntity WHERE name == :name")
    fun getPermissionByName(name: String): List<PermissionEntity>

    @Query("SELECT * FROM PermissionEntity")
    fun getPermissions(): List<PermissionEntity>

    @Query("DELETE FROM PermissionEntity")
    fun deleteAllPermissions()
}