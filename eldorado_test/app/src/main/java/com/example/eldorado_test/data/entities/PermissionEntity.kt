package com.example.eldorado_test.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PermissionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val status : Boolean)