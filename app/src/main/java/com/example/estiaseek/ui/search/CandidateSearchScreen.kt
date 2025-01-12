package com.example.estiaseek.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.estiaseek.R
import com.example.estiaseek.ui.components.DropdownMenuField
import com.example.estiaseek.ui.viewmodels.SearchUiState
import com.example.estiaseek.ui.viewmodels.SearchViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.estiaseek.ui.components.BottomNavigationBar
import com.example.estiaseek.ui.navigation.NavigationHelper
import com.example.estiaseek.ui.viewmodels.CandidateSearchViewModel


@Composable
fun CandidateSearchScreen(
    onSearchButtonClicked: (SearchUiState) -> Unit,
    searchViewModel : SearchViewModel,
    viewModel: CandidateSearchViewModel,
    navController: NavController
    ) {

    val searchUiState by searchViewModel.searchUIState.collectAsState()

    val context = LocalContext.current

    // Get the string arrays from resources and add "Any" option at the start
    val anyOption = stringResource(R.string.any)

    val jobTitles = listOf(anyOption) + context.resources.getStringArray(R.array.job_titles).toList()
    val locations = listOf(anyOption) + context.resources.getStringArray(R.array.locations).toList()
    val experienceLevels = listOf(anyOption) + context.resources.getStringArray(R.array.experience_levels).toList()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onSearchIconClicked = { navController.navigate(NavigationHelper.Search.name) },
                onStartIconClicked = { navController.navigate(NavigationHelper.Start.name) },
                showSearchIcon = false
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(20.dp).padding(paddingValues)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .verticalScroll(rememberScrollState())
                        .safeDrawingPadding()
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Header Section
                    Column(
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.search_candidates),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(R.string.search_message),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    // Search Fields Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            DropdownMenuField(
                                label = R.string.job_title,
                                options = jobTitles,
                                selectedOption = searchUiState.selectedJobTitle.ifEmpty { anyOption },
                                onOptionSelected = {
                                    searchViewModel.updateSearchState(
                                        newState = searchUiState.copy(
                                            selectedJobTitle = it
                                        )
                                    )
                                },
                                icon = Icons.Rounded.Person,
                                modifier = Modifier.fillMaxWidth()
                            )
                            
                            DropdownMenuField(
                                label = R.string.location,
                                options = locations,
                                selectedOption = searchUiState.selectedLocation.ifEmpty { anyOption },
                                onOptionSelected = {
                                    searchViewModel.updateSearchState(
                                        newState = searchUiState.copy(
                                            selectedLocation = it
                                        )
                                    )
                                },
                                icon = Icons.Rounded.LocationOn,
                                modifier = Modifier.fillMaxWidth()
                            )

                            DropdownMenuField(
                                label = R.string.experience_level,
                                options = experienceLevels,
                                selectedOption = searchUiState.selectedExperience.ifEmpty { anyOption },
                                onOptionSelected = {
                                    searchViewModel.updateSearchState(
                                        newState = searchUiState.copy(
                                            selectedExperience = it
                                        )
                                    )
                                },
                                icon = Icons.Rounded.Star,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    // Search Button
                    Button(
                        onClick = {
                            onSearchButtonClicked(searchUiState)
                            viewModel.searchCandidates(
                                searchUiState.selectedJobTitle,
                                searchUiState.selectedLocation,
                                searchUiState.selectedExperience
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Rounded.Search, contentDescription = null)
                            Text(
                                stringResource(R.string.search),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
    }
}