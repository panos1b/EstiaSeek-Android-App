package com.example.estiaseek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.estiaseek.ui.screens.CreateApplicant
import com.example.estiaseek.ui.screens.CandidateSearchScreen
import com.example.estiaseek.ui.theme.EstiaSeekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstiaSeekTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    // Toggle between screens dynamically
    val showApplicantScreen = remember { mutableStateOf(true) }

    if (showApplicantScreen.value) {
        CreateApplicant()
    } else {
        CandidateSearchScreen()
    }
}
