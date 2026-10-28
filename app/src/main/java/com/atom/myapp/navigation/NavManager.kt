package com.atom.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atom.myapp.views.HomeScreen
import com.atom.myapp.views.LoginScreen
import com.atom.myapp.views.RegisterScreen

@Composable
fun NavManager() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            RegisterScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
    }
}