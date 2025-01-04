package com.example.estiaseek.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class SearchUiState(
    val selectedJobTitle: String = "",
    val selectedLocation: String = "",
    val selectedExperience: String = "",
)

class SearchViewModel : ViewModel() {
    private val _searchUIState = MutableStateFlow(SearchUiState())
    val searchUIState: StateFlow<SearchUiState> get() = _searchUIState

    fun updateSearchState(newState: SearchUiState) {
        _searchUIState.value = newState
    }
}
