package com.example.estiaseek.data

import android.content.Context
import android.net.Uri
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

class Converters {
    @TypeConverter
    fun fromString(value: String?): Uri? {
        return value?.let { Uri.parse(it) }
    }

    @TypeConverter
    fun uriToString(uri: Uri?): String? {
        return uri?.toString()
    }
}

@Database(entities = [User::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EstiaSeekDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: EstiaSeekDatabase? = null

        fun getDatabase(context: Context): EstiaSeekDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, EstiaSeekDatabase::class.java, "estiaseek_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}