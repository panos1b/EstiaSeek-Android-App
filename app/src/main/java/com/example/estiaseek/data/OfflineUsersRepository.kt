package com.example.estiaseek.data

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: UserDao) : UsersRepository {
    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

    override fun getUserByIdStream(id: Int): Flow<User?> = userDao.getUserById(id)

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)

    override fun getUsersByExperience(experience: String): Flow<List<User>> = userDao.getUsersByExperience(experience)

    override fun getUsersByLocation(location: String): Flow<List<User>> = userDao.getUsersByLocation(location)

    override fun getUsersByJobTitle(jobTitle: String): Flow<List<User>> = userDao.getUsersByJobTitle(jobTitle)

    override suspend fun updateExperience(id: Int, experience: String) = userDao.updateExperience(id, experience)

    override suspend fun updateBio(id: Int, bio: String) = userDao.updateBio(id, bio)

    override suspend fun updateLocation(id: Int, location: String) = userDao.updateLocation(id, location)

    override suspend fun updateJobTitle(id: Int, jobTitle: String) = userDao.updateJobTitle(id, jobTitle)
}