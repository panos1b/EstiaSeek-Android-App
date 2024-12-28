package com.example.estiaseek.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.estiaseek.R
import com.example.estiaseek.ui.components.DropdownMenuField

@Composable
fun CreateApplicant() {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var selectedJobTitle by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf("") }
    var selectedExperience by remember { mutableStateOf("") }

    val jobTitles = listOf("Any", "Server", "Chef", "Bartender", "Receptionist")
    val locations = listOf(
        "Any", "Attica", "Macedonia and Thrace", "Epirus and Western Macedonia",
        "Thessaly and Central Greece", "Peloponnese and Western Greece", "Aegean",
        "Crete", "Monastic community of Mount Athos"
    )
    val experienceLevels = listOf("Any", "Entry Level", "Intermediate", "Senior")

    val fieldShape = RoundedCornerShape(8.dp)
    val fieldModifier = Modifier.height(75.dp)

    // Ensure the content is scrollable
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(horizontal = 40.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Space between elements
        ) {
            Text(
                text = stringResource(R.string.create_applicant),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 40.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Row for Name and Surname
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            text = stringResource(R.string.name),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Left
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    modifier = Modifier
                        .weight(1f)
                        .then(fieldModifier),
                    shape = fieldShape,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    singleLine = true
                )

                OutlinedTextField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = {
                        Text(
                            text = stringResource(R.string.surname),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Left
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    modifier = Modifier
                        .weight(1f)
                        .then(fieldModifier),
                    shape = fieldShape,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    singleLine = true
                )
            }

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = stringResource(R.string.email),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left
                    )
                },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .then(fieldModifier),
                shape = fieldShape,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
                singleLine = true
            )

            // Job Title Dropdown
            DropdownMenuField(
                label = R.string.job_title,
                options = jobTitles,
                selectedOption = selectedJobTitle,
                onOptionSelected = { selectedJobTitle = it },
                icon = Icons.Default.Person,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .clip(fieldShape)
                    .then(fieldModifier)
            )

            // Bio Field
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text(stringResource(R.string.bio)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .height(120.dp),
                shape = fieldShape,
                minLines = 4
            )

            // Location Dropdown
            DropdownMenuField(
                label = R.string.location,
                options = locations,
                selectedOption = selectedLocation,
                onOptionSelected = { selectedLocation = it },
                icon = Icons.Default.LocationOn,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .clip(fieldShape)
                    .then(fieldModifier)
            )

            // Experience Dropdown
            DropdownMenuField(
                label = R.string.experience_level,
                options = experienceLevels,
                selectedOption = selectedExperience,
                onOptionSelected = { selectedExperience = it },
                icon = Icons.Default.Star,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .clip(fieldShape)
                    .then(fieldModifier)
            )

            // Submit Button
            Button(
                onClick = { /* Search action to be implemented */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = fieldShape
            ) {
                Text(stringResource(R.string.submit))
            }
        }
    }
}
