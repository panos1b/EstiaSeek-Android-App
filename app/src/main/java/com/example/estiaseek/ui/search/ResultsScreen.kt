package com.example.estiaseek.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.estiaseek.R
import com.example.estiaseek.ui.components.BottomNavigationBar
import com.example.estiaseek.ui.components.ImageCard
import com.example.estiaseek.ui.navigation.NavigationHelper
import com.example.estiaseek.ui.viewmodels.CandidateSearchViewModel
import com.example.estiaseek.ui.viewmodels.ProfileViewModel
import com.example.estiaseek.ui.viewmodels.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(
    onProfileClicked: () -> Unit,
    searchViewModel: SearchViewModel,
    profileViewModel: ProfileViewModel,
    viewModel: CandidateSearchViewModel,
    navController: NavController
) {
    val profileViewState by profileViewModel.profileViewState.collectAsState()
    val searchUiState by searchViewModel.searchUIState.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    var selectedJobTitle by remember { mutableStateOf(searchUiState.selectedJobTitle) }
    var selectedLocation by remember { mutableStateOf(searchUiState.selectedLocation) }
    var selectedExperience by remember { mutableStateOf(searchUiState.selectedExperience) }

    fun updateSearch() {
        viewModel.searchCandidates(selectedJobTitle, selectedLocation, selectedExperience)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onSearchIconButtonClicked = { navController.navigate(NavigationHelper.Search.name) },
                onStartIconButtonClicked = { navController.navigate(NavigationHelper.Start.name) }
            )
        }
    ) { paddingValues ->
        Button(
            onClick = {
                profileViewModel.updateProfileView(
                    newState = profileViewState.copy(
                        username = "panos1b"
                    )
                )
                onProfileClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "GO TO PROFILE DEMO BUTTON")
        }

        Column(modifier = Modifier.padding(35.dp).padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.results),
                    fontSize = 35.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.results_based_on_search_terms),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            val filters = listOf(
                searchUiState.selectedJobTitle,
                searchUiState.selectedLocation,
                searchUiState.selectedExperience
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filters.size) { index ->
                            var selected by remember { mutableStateOf(false) }

                            val label = when (index) {
                                0 -> selectedJobTitle
                                1 -> selectedLocation
                                2 -> selectedExperience
                                else -> ""
                            }

                            InputChip(
                                selected = selected,
                                onClick = {
                                    selected = !selected

                                    when (index) {
                                        0 -> selectedJobTitle = if (selected) "Any" else searchUiState.selectedJobTitle
                                        1 -> selectedLocation = if (selected) "Any" else searchUiState.selectedLocation
                                        2 -> selectedExperience = if (selected) "Any" else searchUiState.selectedExperience
                                    }

                                    updateSearch()
                                },
                                label = { Text(label) },
                                trailingIcon = {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Localized description",
                                        Modifier.size(InputChipDefaults.IconSize),
                                        tint = Color.White
                                    )
                                },
                                colors = InputChipDefaults.inputChipColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    labelColor = Color.White
                                ),
                                border = InputChipDefaults.inputChipBorder(
                                    borderColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }

                items(searchResults.size / 2 + searchResults.size % 2) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val firstItemIndex = rowIndex * 2
                        val firstItem = searchResults[firstItemIndex]
                        ImageCard(
                            title = firstItem.name,
                            imageRes = R.drawable.dummy_restaurant_photo,
                            modifier = Modifier
                                .weight(1f)
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )

                        if (firstItemIndex + 1 < searchResults.size) {
                            val secondItem = searchResults[firstItemIndex + 1]
                            ImageCard(
                                title = secondItem.name,
                                imageRes = R.drawable.dummy_restaurant_photo,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}