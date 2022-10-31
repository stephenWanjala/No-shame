package com.wantech.noshame.feature_auth.presentation.signUp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wantech.noshame.feature_auth.presentation.util.Screen

@Composable
fun SigUpScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        IConWithText(
            modifier = Modifier.align(Alignment.TopCenter), onClick = {
                navController.navigate(Screen.SignIn.route) {
                    popUpTo(Screen.SignIn.route)
                }
            },
            text = "Sign Up Account"
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center),
            contentColor = MaterialTheme.colors.surface,
            backgroundColor = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(12.dp),

            ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {

                Text(
                    text = "Create Account",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, top = 32.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal,
                    style = MaterialTheme.typography.h6
                )

                SignUpTextFields(buttonLabel = "Sign Up", signUpFinally = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route)
                    }
                }) {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.SignIn.route)
                    }

                }
            }

        }
    }


}




