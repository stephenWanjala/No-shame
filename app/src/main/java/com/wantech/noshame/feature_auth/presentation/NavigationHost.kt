package com.wantech.noshame.feature_auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.noshame.featureMenstrualTrack.presentation.home.HomeScreen
import com.wantech.noshame.feature_auth.presentation.forgotPassword.components.ForgotPasswordScreen
import com.wantech.noshame.feature_auth.presentation.login.LoginScreen
import com.wantech.noshame.feature_auth.presentation.signUp.components.MoreInformationScreen
import com.wantech.noshame.feature_auth.presentation.signUp.components.SigUpScreen
import com.wantech.noshame.feature_auth.presentation.util.Screen

@Composable
fun NavigationHost(
    navController: NavHostController,

    ) {

    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route,

        ) {
        composable(route = Screen.SignIn.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.SignUp.route) {
            SigUpScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(route = Screen.MoreInfoScreen.route) {
            MoreInformationScreen(navController = navController)
        }
    }
}