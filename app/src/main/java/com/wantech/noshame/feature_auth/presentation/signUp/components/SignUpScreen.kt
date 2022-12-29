package com.wantech.noshame.feature_auth.presentation.signUp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

        Header(
            headerText = "Sign Up",
            headerEndText = "1/2",
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center),
//            contentColor = MaterialTheme.colors.surface,
//            backgroundColor = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(12.dp),

            ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
//                    .align(Alignment.Center)
            ) {

                Text(
                    text = "Create Account",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, top = 32.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal,
                    style = MaterialTheme.typography.headlineSmall
                )

                SignUpTextFields(buttonLabel = "Next", toMoreInforScreen = {
                    navController.navigate(Screen.MoreInfoScreen.route) {
                        popUpTo(
                            Screen.MoreInfoScreen
                                .route
                        )
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




