package com.wantech.noshame.feature_auth.presentation.util

sealed class Screen(val route: String) {
    init {
        require(route.isNotBlank()) { "Route must not be blank" }
    }

    object Home : Screen("home")
    object SignUp : Screen("sign_up")
    object SignIn : Screen("sign_in")
    object ForgotPassword : Screen("forgot_password")
    object MoreInfoScreen : Screen(route = "more_information")
    object FAQScreen : Screen(route = "FAQs")
    object AboutScreen : Screen(route = "about")
    object TermsAndConditionsScreen : Screen(route = "terms_and_conditions")
    object HygieneTipsScreen : Screen(route = "hygiene_tips")
    object ExploreScreen : Screen(route = "explore")
    object MythsScreen : Screen(route = "Menstrual myths")
}