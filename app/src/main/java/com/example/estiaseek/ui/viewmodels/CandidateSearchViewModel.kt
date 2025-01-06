package com.example.estiaseek.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estiaseek.data.User
import com.example.estiaseek.data.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CandidateSearchViewModel(private val repository: UsersRepository) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<User>>(emptyList())
    val searchResults: StateFlow<List<User>> = _searchResults.asStateFlow()

    fun searchCandidates(jobTitle: String, location: String, experience: String) {
        viewModelScope.launch {
            repository.getAllUsersStream()
                .collect { users ->
                    _searchResults.value = users.filter { user ->
                        (jobTitle == "Any" || user.jobTitle == jobTitle) &&
                                (location == "Any" || user.location == location) &&
                                (experience == "Any" || user.experience == experience)
                    }
                }
        }
    }
}

