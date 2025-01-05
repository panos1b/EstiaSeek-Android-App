package com.example.estiaseek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.estiaseek.data.EstiaSeekDatabase
import com.example.estiaseek.data.OfflineUsersRepository
import com.example.estiaseek.ui.screens.CandidateSearchScreen
import com.example.estiaseek.ui.screens.CandidateSearchViewModel
import com.example.estiaseek.ui.screens.CandidateSearchViewModelFactory
import com.example.estiaseek.ui.screens.CreateApplicantViewModel
import com.example.estiaseek.ui.theme.EstiaSeekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the database and repository
        val database = EstiaSeekDatabase.getDatabase(applicationContext)
        val usersRepository = OfflineUsersRepository(database.userDao())

        // Create the ViewModel using the factory
        val factory = CandidateSearchViewModelFactory(usersRepository)
        val candidateSearchViewModel: CandidateSearchViewModel = ViewModelProvider(this, factory).get(CandidateSearchViewModel::class.java)

        //FIXME
        val createApplicantViewModel : CreateApplicantViewModel = CreateApplicantViewModel(usersRepository=usersRepository)

        setContent {
            EstiaSeekTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EstiaSeekScreen(
                        viewModel = candidateSearchViewModel,
                        createApplicantViewModel = createApplicantViewModel
                    )
                }
            }
        }
    }
}
