package com.wantech.noshame.feature_auth.presentation.util

sealed class Screen(val route: String) {
    init {
        require(route.isNotBlank()) { "Route must not be blank" }
    }

    data object Home : Screen("home")
    data object SignUp : Screen("sign_up")
    data object SignIn : Screen("sign_in")
    data object ForgotPassword : Screen("forgot_password")
    data object MoreInfoScreen : Screen(route = "more_information")
    data object FAQScreen : Screen(route = "FAQs")
    data object AboutScreen : Screen(route = "about")
    data object TermsAndConditionsScreen : Screen(route = "terms_and_conditions")
    data object HygieneTipsScreen : Screen(route = "hygiene_tips")
    data object ExploreScreen : Screen(route = "explore")
    data object MythsScreen : Screen(route = "Menstrual myths")
}