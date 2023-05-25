package com.wantech.noshame.feature_auth.presentation.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wantech.noshame.R
import com.wantech.noshame.feature_auth.presentation.login.componets.TextInPutSection
import com.wantech.noshame.feature_auth.presentation.util.Screen

@Composable
fun LoginScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .align(Alignment.Center),
//            contentColor = MaterialTheme.colors.surface,
//            backgroundColor = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(12.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                TextInPutSection(
                    buttonLabel = stringResource(id = R.string.sign_in),
                    onClickLoginButton = {
                        navController.navigate("home_nav") {
                            popUpTo(route = "auth_nav") {
                                inclusive = true
                            }
                        }
                    },
                    onClickToSignUp = {
                        navController.navigate(Screen.SignUp.route) {
                            popUpTo(Screen.SignUp.route) {
                                inclusive = true
                            }
                        }
                    },
                    onForgetPassword = {
                        navController.navigate(Screen.ForgotPassword.route) {
                            popUpTo(Screen.ForgotPassword.route) {
                                inclusive = true
                            }
                        }
                    }
                )

            }
        }


    }


}




