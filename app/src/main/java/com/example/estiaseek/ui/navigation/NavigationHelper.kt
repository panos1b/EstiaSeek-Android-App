package com.example.estiaseek.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.estiaseek.ui.profile.ApplicantProfile
import com.example.estiaseek.ui.search.CandidateSearchScreen
import com.example.estiaseek.ui.viewmodels.CandidateSearchViewModel
import com.example.estiaseek.ui.profile.CreateApplicant
import com.example.estiaseek.ui.viewmodels.CreateApplicantViewModel
import com.example.estiaseek.ui.HomeScreen
import com.example.estiaseek.ui.components.BottomNavigationBar
import com.example.estiaseek.ui.search.ResultsScreen
import com.example.estiaseek.ui.viewmodels.ProfileViewModel
import com.example.estiaseek.ui.viewmodels.SearchViewModel


enum class NavigationHelper() {
    Start,
    CreateApplicant,
    ShowResults,
    Profile,
    Search,
    BottomNavBar
}

@Composable
fun NavigationHelper(
    navController: NavHostController = rememberNavController(),
    searchViewModel: SearchViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel(),
    viewModel : CandidateSearchViewModel,
    createApplicantViewModel : CreateApplicantViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationHelper.Start.name,
        modifier = Modifier,
    ) {
        composable(route = NavigationHelper.Start.name) {
            HomeScreen(
                onSearchButtonClicked = { navController.navigate(NavigationHelper.Search.name) },
                onCreateApplicantButtonClicked = { navController.navigate(NavigationHelper.CreateApplicant.name) }
            )
        }
        composable(route = NavigationHelper.CreateApplicant.name) {
            CreateApplicant(
                onCreateApplicantButtonClicked = { navController.navigate(NavigationHelper.Start.name)},
                navController = navController,
                viewModel = createApplicantViewModel //fixme
            )
        }
        composable(route = NavigationHelper.ShowResults.name) {
            ResultsScreen(
                onProfileClicked = { navController.navigate(NavigationHelper.Profile.name)},
                searchViewModel = searchViewModel,
                profileViewModel = profileViewModel,
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = NavigationHelper.Profile.name) {
            ApplicantProfile(
                profileViewModel = profileViewModel,
                navController = navController
            )
        }
        composable(route = NavigationHelper.Search.name) {
            CandidateSearchScreen(
                onSearchButtonClicked = { navController.navigate(NavigationHelper.ShowResults.name)},
                searchViewModel = searchViewModel,
                viewModel = viewModel,
                navController = navController
                )
        }
        composable(route = NavigationHelper.BottomNavBar.name) {
            BottomNavigationBar(
                onSearchIconClicked = { navController.navigate(NavigationHelper.Search.name)},
                onStartIconClicked = { navController.navigate(NavigationHelper.Start.name)}
            )
        }
    }
}


