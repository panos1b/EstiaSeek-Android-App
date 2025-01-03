package com.example.estiaseek.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.estiaseek.R
import com.example.estiaseek.ui.theme.EstiaSeekTheme

@Composable
fun HomeScreen() {
    EstiaSeekTheme { // Ensure the app's theme is applied
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background // Consistent background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .safeDrawingPadding()
                    .padding(horizontal = 40.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Heading
                    Text(
                        text = "Welcome to EstiaSeek",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold // Make the text bold
                        ),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Image
                    Image(
                        painter = painterResource(id = R.drawable.emp),
                        contentDescription = "Example Image",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(bottom = 24.dp)
                    )

                    // First Button
                    Button(
                        onClick = { /* TODO: Add action */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(text = "Search Candidate")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Second Button
                    Button(
                        onClick = { /* TODO: Add action */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(text = "Create Applicant")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
