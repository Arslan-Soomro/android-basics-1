// Illustrates compose navigation with NavHost

package com.example.madmid.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.madmid.MainScreenUI
import com.example.madmid.activities.ChatUI
import com.example.madmid.activities.WaterCounterUI

@Composable
fun Navigation() {
    val navController = rememberNavController();
    val waterCounterArgs = listOf(
        navArgument(name = "count") {
            type = NavType.IntType
        }
    )

    NavHost(navController, startDestination = "home") {
        composable(route = "home") { MainScreenUI(navController = navController) }
        composable(route = "water_counter/{count}", waterCounterArgs) { backStackEntry ->
            val countVal = backStackEntry.arguments?.getInt("count") ?: 0;
            WaterCounterUI(countVal);
        }
        composable(route = "chat") {
            ChatUI()
        }
        composable(route = "preferences") {
            PreferencesUI();
        }
    }
}