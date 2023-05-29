package com.wantech.noshame.feature_auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wantech.noshame.fAQs.presentation.FAQSScreen
import com.wantech.noshame.featureMenstrualTrack.presentation.home.HomeScreen
import com.wantech.noshame.feature_auth.presentation.forgotPassword.components.ForgotPasswordScreen
import com.wantech.noshame.feature_auth.presentation.login.LoginScreen
import com.wantech.noshame.feature_auth.presentation.signUp.SigUpScreen
import com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo.MoreInformationScreen
import com.wantech.noshame.feature_auth.presentation.util.Screen

@Composable
fun NavigationHost(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = "auth_nav",

        ) {

        authNav(navController = navController)

        homeGraph(navController = navController)


    }

}

private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Home.route, route = "home_nav") {

        composable(route = Screen.Home.route) {
            HomeScreen(navHostController = navController)
        }
        composable(route = Screen.FAQScreen.route) {
            FAQSScreen()
        }
    }
}


private fun NavGraphBuilder.authNav(navController: NavHostController) {
    navigation(startDestination = Screen.SignIn.route, route = "auth_nav") {
        composable(route = Screen.SignIn.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.SignUp.route) {
            SigUpScreen(navController = navController)
        }
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(route = Screen.MoreInfoScreen.route) {
            MoreInformationScreen(navController = navController)
        }

    }
}