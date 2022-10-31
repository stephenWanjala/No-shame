package com.wantech.noshame.feature_auth.presentation.util

sealed class Screen(val route:String){
    object Home:Screen("home")
    object SignUp:Screen("sign_up")
    object SignIn:Screen("sign_in")
    object ForgotPassword:Screen("forgot_password")
}