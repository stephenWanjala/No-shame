package com.wantech.noshame.feature_auth.presentation.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wantech.noshame.R
import com.wantech.noshame.feature_auth.presentation.login.componets.TextInPutSection
import com.wantech.noshame.feature_auth.presentation.util.Screen

@Composable
fun LoginScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
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


                Text(
                    text = stringResource(R.string.sign_in_welcome_text),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, top = 32.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.background
                )
                TextInPutSection(
                    buttonLabel = stringResource(id = R.string.sign_in),
                    onClickLoginButton = { navController.navigate(Screen.Home.route) },
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



        Text(
            text = stringResource(R.string.login_Account),
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 32.dp, bottom = 16.dp, end = 32.dp, top = 16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))


    }


}




