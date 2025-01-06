package com.example.estiaseek.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class SearchUiState(
    val selectedJobTitle: String = "Any",
    val selectedLocation: String = "Any",
    val selectedExperience: String = "Any",
)

class SearchViewModel : ViewModel() {
    private val _searchUIState = MutableStateFlow(SearchUiState())
    val searchUIState: StateFlow<SearchUiState> get() = _searchUIState

    fun updateSearchState(newState: SearchUiState) {
        _searchUIState.value = newState
    }
}
