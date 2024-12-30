package com.example.estiaseek.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estiaseek.data.UsersRepository
import com.example.estiaseek.data.User
import kotlinx.coroutines.launch
import java.util.UUID

class CreateApplicantViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    fun saveApplicant(
        name: String,
        surname: String,
        email: String,
        bio: String,
        jobTitle: String,
        location: String,
        experience: String
    ) {
        // Generate a random password for now since it's not in the UI yet
        val randomPassword = UUID.randomUUID().toString()

        val user = User(
            name = name,
            email = email,
            password = randomPassword,
            bio = bio,
            experience = experience,
            location = location,
            jobTitle = jobTitle
        )

        viewModelScope.launch {
            usersRepository.insertUser(user)
        }
    }
}