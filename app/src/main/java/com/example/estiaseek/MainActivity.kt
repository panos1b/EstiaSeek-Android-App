package com.example.estiaseek

import android.annotation.SuppressLint
import android.database.CursorWindow
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
import com.example.estiaseek.ui.navigation.NavigationHelper
import com.example.estiaseek.ui.theme.EstiaSeekTheme
import com.example.estiaseek.ui.viewmodels.CandidateSearchViewModel
import com.example.estiaseek.ui.viewmodels.CandidateSearchViewModelFactory
import com.example.estiaseek.ui.viewmodels.CreateApplicantViewModel
import java.lang.reflect.Field


class MainActivity : ComponentActivity() {
    @SuppressLint("DiscouragedPrivateApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field.set(null, 100 * 1024 * 1024) //the 100MB is the new size
        } catch (e: Exception) {
            e.printStackTrace()
        }

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
                    NavigationHelper(
                        viewModel = candidateSearchViewModel,
                        createApplicantViewModel = createApplicantViewModel
                    )
                }
            }
        }
    }
}
