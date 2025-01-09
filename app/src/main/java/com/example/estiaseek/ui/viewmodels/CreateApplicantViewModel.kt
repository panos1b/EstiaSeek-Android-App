package com.example.estiaseek.ui.viewmodels

import android.net.Uri
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estiaseek.data.User
import com.example.estiaseek.data.UsersRepository
import kotlinx.coroutines.launch

class CreateApplicantViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    // Validation function that returns error keys
    fun validateApplicant(
        name: String,
        email: String,
        bio: String,
        jobTitle: String,
        location: String,
        experience: String,
        photoUri: Uri?
    ): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        if (name.isBlank()) {
            errors["name"] = "name_required"
        }
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors["email"] = "email_required"
        }
        if (jobTitle.isBlank()) {
            errors["jobTitle"] = "job_title_required"
        }
        if (bio.isBlank()) {
            errors["bio"] = "bio_required"
        }
        if (location.isBlank()) {
            errors["location"] = "location_required"
        }
        if (experience.isBlank()) {
            errors["experience"] = "experience_required"
        }
        if (photoUri == null) {
            errors["photo"] = "photo_required"
        }

        return errors
    }

    // Save applicant logic
    fun saveApplicant(
        name: String,
        email: String,
        bio: String,
        jobTitle: String,
        location: String,
        experience: String,
        photoUri: Uri?
    ) {

        val user = User(
            name = name,
            email = email,
            bio = bio,
            experience = experience,
            location = location,
            jobTitle = jobTitle,
            photoUri = photoUri
        )

        viewModelScope.launch {
            usersRepository.insertUser(user)
        }
    }
}