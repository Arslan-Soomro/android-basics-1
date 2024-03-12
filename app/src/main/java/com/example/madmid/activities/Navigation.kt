// Illustrates compose navigation with NavHost

package com.example.madmid.activities

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.madmid.MainScreenUI

@Composable
fun Navigation() {
    val navController = rememberNavController();
    NavHost(navController, startDestination = "home") {
        composable(route = "home") { MainScreenUI(navController = navController) }
        composable(route = "water_counter/{count}") { backStackEntry ->
            val countVal = backStackEntry.arguments?.getInt("count") ?: 0;
            WaterCounterUI(counter = countVal);
        }
        composable(route = "chat") {
            ChatUI()
        }
    }
}