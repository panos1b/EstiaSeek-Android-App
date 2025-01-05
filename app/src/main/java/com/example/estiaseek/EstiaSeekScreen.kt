package com.example.estiaseek

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.estiaseek.ui.profile.Profile
import com.example.estiaseek.ui.screens.CandidateSearchScreen
import com.example.estiaseek.ui.screens.CandidateSearchViewModel
import com.example.estiaseek.ui.screens.CreateApplicant
import com.example.estiaseek.ui.screens.CreateApplicantViewModel
import com.example.estiaseek.ui.screens.HomeScreen
import com.example.estiaseek.ui.screens.ResultsScreen
import com.example.estiaseek.ui.viewmodels.ProfileViewModel
import com.example.estiaseek.ui.viewmodels.SearchViewModel


enum class EstiaSeekScreen() {
    Start,
    CreateApplicant,
    ShowResults,
    Profile,
    Search
}

@Composable
fun EstiaSeekScreen(
    navController: NavHostController = rememberNavController(),
    searchViewModel: SearchViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel(),
    viewModel : CandidateSearchViewModel,
    createApplicantViewModel : CreateApplicantViewModel
) {
    NavHost(
        navController = navController,
        startDestination = EstiaSeekScreen.Start.name,
        modifier = Modifier,
    ) {
        composable(route = EstiaSeekScreen.Start.name) {
            HomeScreen(
                onSearchButtonClicked = { navController.navigate(EstiaSeekScreen.Search.name) },
                onCreateApplicantButtonClicked = { navController.navigate(EstiaSeekScreen.CreateApplicant.name) }
            )
        }
        composable(route = EstiaSeekScreen.CreateApplicant.name) {
            CreateApplicant(
                onCreateApplicantButtonClicked = { navController.navigate(EstiaSeekScreen.Start.name)},
                 viewModel = createApplicantViewModel //fixme
            )
        }
        composable(route = EstiaSeekScreen.ShowResults.name) {
            ResultsScreen(
                onProfileClicked = { navController.navigate(EstiaSeekScreen.Profile.name)},
                searchViewModel = searchViewModel,
                profileViewModel = profileViewModel
            )
        }
        composable(route = EstiaSeekScreen.Profile.name) {
            Profile(
                profileViewModel = profileViewModel
            )
        }
        composable(route = EstiaSeekScreen.Search.name) {
            CandidateSearchScreen(
                onSearchButtonClicked = { navController.navigate(EstiaSeekScreen.ShowResults.name)},
                searchViewModel = searchViewModel,
                viewModel = viewModel
                )
        }
    }

}


