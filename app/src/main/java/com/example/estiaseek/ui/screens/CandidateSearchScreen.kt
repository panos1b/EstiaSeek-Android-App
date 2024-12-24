package com.example.estiaseek.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.estiaseek.R
import com.example.estiaseek.ui.components.DropdownMenuField
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CandidateSearchScreen() {
    var selectedJobTitle by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf("") }
    var selectedExperience by remember { mutableStateOf("") }

    val jobTitles = listOf("Any", "Server", "Chef", "Bartender", "Receptionist")
    val locations = listOf(
        "Any",
        "Attica",
        "Macedonia and Thrace",
        "Epirus and Western Macedonia",
        "Thessaly and Central Greece",
        "Peloponnese and Western Greece",
        "Aegean",
        "Crete",
        "Monastic community of Mount Athos"
    )
    val experienceLevels = listOf("Any", "Entry Level", "Intermediate", "Senior")

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
                        selectedOption = selectedJobTitle,
                        onOptionSelected = { selectedJobTitle = it },
                        icon = Icons.Rounded.Person,
                        modifier = Modifier.fillMaxWidth()
                    )

                    DropdownMenuField(
                        label = R.string.location,
                        options = locations,
                        selectedOption = selectedLocation,
                        onOptionSelected = { selectedLocation = it },
                        icon = Icons.Rounded.LocationOn,
                        modifier = Modifier.fillMaxWidth()
                    )

                    DropdownMenuField(
                        label = R.string.experience_level,
                        options = experienceLevels,
                        selectedOption = selectedExperience,
                        onOptionSelected = { selectedExperience = it },
                        icon = Icons.Rounded.Star,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Search Button
            Button(
                onClick = { /* Search action to be implemented */ },
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