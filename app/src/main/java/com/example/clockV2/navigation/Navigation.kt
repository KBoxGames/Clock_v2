package com.example.clockV2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clockV2.ui.main.MainScreen
import com.example.clockV2.ui.main.MainViewModel
import com.example.clockV2.ui.settings.SettingsScreen

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Settings : Screen("settings")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel ()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(

                viewModel = viewModel
            )
        }
        composable(Screen.Settings.route) {

        }
    }
}