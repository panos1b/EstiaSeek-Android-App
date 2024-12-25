package com.example.estiaseek.data

import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getAllUsersStream(): Flow<List<User>>

    fun getUserByIdStream(id: Int): Flow<User?>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)

    fun getUsersByExperience(experience: String): Flow<List<User>>

    fun getUsersByLocation(location: String): Flow<List<User>>

    fun getUsersByJobTitle(jobTitle: String): Flow<List<User>>

    suspend fun updateExperience(id: Int, experience: String)

    suspend fun updateBio(id: Int, bio: String)

    suspend fun updateLocation(id: Int, location: String)

    suspend fun updateJobTitle(id: Int, jobTitle: String)
}