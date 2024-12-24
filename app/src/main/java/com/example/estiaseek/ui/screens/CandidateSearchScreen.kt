package com.example.estiaseek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star

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

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.search_candidates),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(Alignment.Start)
        )

        DropdownMenuField(
            label = R.string.job_title,
            options = jobTitles,
            selectedOption = selectedJobTitle,
            onOptionSelected = { selectedJobTitle = it },
            icon = Icons.Default.Person,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        DropdownMenuField(
            label = R.string.location,
            options = locations,
            selectedOption = selectedLocation,
            onOptionSelected = { selectedLocation = it },
            icon = Icons.Default.LocationOn,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        DropdownMenuField(
            label = R.string.experience_level,
            options = experienceLevels,
            selectedOption = selectedExperience,
            onOptionSelected = { selectedExperience = it },
            icon = Icons.Default.Star,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )


        Button(
            onClick = { /* Search action to be implemented */ },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.search))
        }
    }
}