package com.example.estiaseek.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.estiaseek.data.UsersRepository

class CreateApplicantViewModelFactory(
    private val usersRepository: UsersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateApplicantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateApplicantViewModel(usersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}