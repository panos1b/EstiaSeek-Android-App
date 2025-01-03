package com.example.estiaseek

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.estiaseek.ui.screens.CreateApplicant
import com.example.estiaseek.ui.screens.HomeScreen
import com.example.estiaseek.ui.profile.Profile
import com.example.estiaseek.ResultsScreen


enum class EstiaSeekScreen() {
    Start,
    CreateApplicant,
    ShowResults,
    Profile
}

@Composable
fun EstiaSeekScreen(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = EstiaSeekScreen.Start.name,
        modifier = Modifier
    ) {
        composable(route = EstiaSeekScreen.Start.name) {
            HomeScreen()
        }
        composable(route = EstiaSeekScreen.CreateApplicant.name) {
            CreateApplicant()
        }
        composable(route = EstiaSeekScreen.ShowResults.name) {
            ResultsScreen()
        }
        composable(route = EstiaSeekScreen.Profile.name) {
            Profile()
        }
    }

}


