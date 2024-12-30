package com.example.estiaseek.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users ORDER BY name")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flow<User>

    @Query("SELECT * FROM users WHERE experience = :experience")
    fun getUsersByExperience(experience: String): Flow<List<User>>

    @Query("SELECT * FROM users WHERE location = :location")
    fun getUsersByLocation(location: String): Flow<List<User>>

    @Query("SELECT * FROM users WHERE jobTitle = :jobTitle")
    fun getUsersByJobTitle(jobTitle: String): Flow<List<User>>

    @Query("UPDATE users SET bio = :bio WHERE id = :id")
    suspend fun updateBio(id: Int, bio: String)

    @Query("UPDATE users SET experience = :experience WHERE id = :id")
    suspend fun updateExperience(id: Int, experience: String)

    @Query("UPDATE users SET location = :location WHERE id = :id")
    suspend fun updateLocation(id: Int, location: String)

    @Query("UPDATE users SET jobTitle = :jobTitle WHERE id = :id")
    suspend fun updateJobTitle(id: Int, jobTitle: String)
}