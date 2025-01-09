package com.example.estiaseek.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 4, exportSchema = false)
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
