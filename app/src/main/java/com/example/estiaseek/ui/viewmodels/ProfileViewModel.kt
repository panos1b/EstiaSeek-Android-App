package com.example.estiaseek.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ProfileViewState(
    val username: String = "",
    val bio: String = "",
    val experience: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val location: String = "",
    val jobTitle: String = "",
    val photoData: ByteArray? = null
)



class ProfileViewModel : ViewModel() {
    private val _profileViewState = MutableStateFlow(ProfileViewState())
    val profileViewState: StateFlow<ProfileViewState> get() = _profileViewState

    fun updateProfileView(newState: ProfileViewState) {
        _profileViewState.value = newState
    }
}
