package com.example.estiaseek.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "PhoneNumber")
    val phoneNumber: String,
    @ColumnInfo(name = "Bio")
    val bio: String,
    @ColumnInfo(name = "Experience")
    val experience: String,
    @ColumnInfo(name = "Location")
    val location: String,
    @ColumnInfo(name = "JobTitle")
    val jobTitle: String,
    @ColumnInfo(name = "PhotoData")
    val photoData: ByteArray? = null
)