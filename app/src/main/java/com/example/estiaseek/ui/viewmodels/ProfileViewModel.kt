package com.example.estiaseek.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ProfileViewState(
    val username: String = "",
)



class ProfileViewModel : ViewModel() {
    private val _profileViewState = MutableStateFlow(ProfileViewState())
    val profileViewState: StateFlow<ProfileViewState> get() = _profileViewState

    fun updateProfileView(newState: ProfileViewState) {
        _profileViewState.value = newState
    }
}
