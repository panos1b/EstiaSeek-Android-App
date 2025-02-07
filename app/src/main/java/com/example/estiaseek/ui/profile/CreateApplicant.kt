package com.example.estiaseek.ui.profile


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.estiaseek.R
import com.example.estiaseek.ui.components.BottomNavigationBar
import com.example.estiaseek.ui.components.CustomSnackbar
import com.example.estiaseek.ui.components.DropdownMenuField
import com.example.estiaseek.ui.navigation.NavigationHelper
import com.example.estiaseek.ui.viewmodels.CreateApplicantViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.InputStream

@Composable
fun CreateApplicant(
    onCreateApplicantButtonClicked: () -> Unit,
    viewModel: CreateApplicantViewModel,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var shouldNavigate by remember { mutableStateOf(false) }

    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            delay(1500)
            onCreateApplicantButtonClicked()
            shouldNavigate = false
        }
    }
    val candidateSaved = stringResource(R.string.candidate_saved)
    val candidateFailed = stringResource(R.string.candidate_saving_failed)

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomSnackbar(
                        snackbarData = data,
                        backgroundColor = if (data.visuals.message == candidateSaved) {
                            Color(0xFF388E3C)
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                onSearchIconClicked = { navController.navigate(NavigationHelper.Search.name) },
                onStartIconClicked = { navController.navigate(NavigationHelper.Start.name) },
                onAddIconClicked = { navController.navigate(NavigationHelper.CreateApplicant.name) },
                showAddIcon = false
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(0.dp).padding(paddingValues)) {
            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }
            var bio by remember { mutableStateOf("") }
            var selectedJobTitle by remember { mutableStateOf("") }
            var selectedLocation by remember { mutableStateOf("") }
            var selectedExperience by remember { mutableStateOf("") }
            var photoUri by remember { mutableStateOf<Uri?>(null) }
            var photoData by remember { mutableStateOf<ByteArray?>(null) } // Updated to ByteArray

            // Error messages
            var errorMessages by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

            val context = LocalContext.current
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                photoUri = uri
                uri?.let {
                    val inputStream: InputStream? = context.contentResolver.openInputStream(it)
                    photoData = inputStream?.readBytes()
                }
            }

            // Get the string arrays from resources with empty string as first option
            val jobTitles = listOf("") + context.resources.getStringArray(R.array.job_titles).toList()
            val locations = listOf("") + context.resources.getStringArray(R.array.locations).toList()
            val experienceLevels = listOf("") + context.resources.getStringArray(R.array.experience_levels).toList()

            val fieldShape = RoundedCornerShape(8.dp)
            val fieldModifier = Modifier.height(75.dp)

            // Mapping error keys to resource strings
            val errorMessagesMap = mapOf(
                "name_required" to context.getString(R.string.name_required),
                "email_required" to context.getString(R.string.email_required),
                "phone_required" to context.getString(R.string.phone_required),
                "job_title_required" to context.getString(R.string.job_title_required),
                "bio_required" to context.getString(R.string.bio_required),
                "location_required" to context.getString(R.string.location_required),
                "experience_required" to context.getString(R.string.experience_required),
                "photo_required" to context.getString(R.string.photo_required)
            )

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
                    Column(
                        modifier = Modifier.padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.create_applicant),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(R.string.create_information),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    // Name Field with Error Message
                    Column(modifier = Modifier.fillMaxWidth()) {
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
                                .fillMaxWidth()
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
                        // Show error message for name if present
                        errorMessages["name"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Email Field with Error Message
                    Column(modifier = Modifier.fillMaxWidth()) {
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
                        // Show error message for email if present
                        errorMessages["email"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Phone Number Field with Error Message
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = {
                                Text(
                                    text = stringResource(R.string.phoneNumber),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Left
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                            modifier = Modifier
                                .fillMaxWidth()
                                .then(fieldModifier),
                            shape = fieldShape,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = null
                                )
                            },
                            singleLine = true
                        )
                        // Show error message for email if present
                        errorMessages["phoneNumber"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Job Title Dropdown with Error Message
                    Column(modifier = Modifier.fillMaxWidth()) {
                        DropdownMenuField(
                            label = R.string.job_title,
                            options = jobTitles,
                            selectedOption = selectedJobTitle,
                            onOptionSelected = { selectedJobTitle = it },
                            icon = Icons.Default.Person,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(fieldShape)
                                .then(fieldModifier)
                        )
                        // Show error message for job title if present
                        errorMessages["jobTitle"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Bio Field with Error Message
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = bio,
                            onValueChange = { bio = it },
                            label = { Text(stringResource(R.string.bio)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            shape = fieldShape,
                            minLines = 4
                        )
                        // Show error message for bio if present
                        errorMessages["bio"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Location Dropdown with Error Message
                    Column(modifier = Modifier.fillMaxWidth()) {
                        DropdownMenuField(
                            label = R.string.location,
                            options = locations,
                            selectedOption = selectedLocation,
                            onOptionSelected = { selectedLocation = it },
                            icon = Icons.Default.LocationOn,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(fieldShape)
                                .then(fieldModifier)
                        )
                        // Show error message for location if present
                        errorMessages["location"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Experience Dropdown with Error Message
                    Column(modifier = Modifier.fillMaxWidth()) {
                        DropdownMenuField(
                            label = R.string.experience_level,
                            options = experienceLevels,
                            selectedOption = selectedExperience,
                            onOptionSelected = { selectedExperience = it },
                            icon = Icons.Default.Star,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(fieldShape)
                                .then(fieldModifier)
                        )
                        // Show error message for experience if present
                        errorMessages["experience"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Photo Upload Field
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { launcher.launch("image/png") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(75.dp),
                            shape = fieldShape
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = null
                                )
                                Text(
                                    text = photoUri?.let {
                                        stringResource(R.string.change_photo)
                                    } ?: stringResource(R.string.upload_photo)
                                )
                            }
                        }

                        // Show selected image preview
                        photoUri?.let { uri ->
                            AsyncImage(
                                model = uri,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(top = 8.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                        // Show error message for photo if present
                        errorMessages["photo"]?.let {
                            Text(
                                text = errorMessagesMap[it] ?: "",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Submit Button
                    Button(
                        onClick = {
                            errorMessages = viewModel.validateApplicant(
                                name = name,
                                email = email,
                                phoneNumber = phoneNumber,
                                bio = bio,
                                jobTitle = selectedJobTitle,
                                location = selectedLocation,
                                experience = selectedExperience,
                                photoData = photoData
                            )
                            if (errorMessages.isEmpty()) {
                                viewModel.saveApplicant(
                                    name = name,
                                    email = email,
                                    phoneNumber = phoneNumber,
                                    bio = bio,
                                    jobTitle = selectedJobTitle,
                                    location = selectedLocation,
                                    experience = selectedExperience,
                                    photoData = photoData
                                )
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = candidateSaved,
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                shouldNavigate = true
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = candidateFailed,
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp),
                        shape = fieldShape
                    ) {
                        Text(stringResource(R.string.submit))
                    }
                }
            }
        }
    }
}
