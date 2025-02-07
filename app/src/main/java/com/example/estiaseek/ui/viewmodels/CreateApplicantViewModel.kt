package com.example.estiaseek.ui.viewmodels

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
        phoneNumber: String,
        bio: String,
        jobTitle: String,
        location: String,
        experience: String,
        photoData: ByteArray?
    ): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        if (name.isBlank()) {
            errors["name"] = "name_required"
        }
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors["email"] = "email_required"
        }
        if (phoneNumber.isBlank() || !Patterns.PHONE.matcher(phoneNumber).matches()) {
            errors["phoneNumber"] = "phone_required"
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
        if (photoData == null) {
            errors["photo"] = "photo_required"
        }

        return errors
    }

    // Save applicant logic
    fun saveApplicant(
        name: String,
        email: String,
        phoneNumber: String,
        bio: String,
        jobTitle: String,
        location: String,
        experience: String,
        photoData: ByteArray?
    ) {

        val user = User(
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            bio = bio,
            experience = experience,
            location = location,
            jobTitle = jobTitle,
            photoData = photoData
        )

        viewModelScope.launch {
            usersRepository.insertUser(user)
        }
    }
}