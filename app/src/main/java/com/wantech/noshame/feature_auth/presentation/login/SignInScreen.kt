package com.wantech.noshame.feature_auth.presentation.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wantech.noshame.R
import com.wantech.noshame.destinations.ForgotPasswordScreenDestination
import com.wantech.noshame.destinations.HomeScreenDestination
import com.wantech.noshame.destinations.LoginScreenDestination
import com.wantech.noshame.destinations.SigUpScreenDestination
import com.wantech.noshame.feature_auth.presentation.login.componets.TextInPutSection

@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Surface(color = MaterialTheme.colorScheme.background) {
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
                            navigator.navigate(HomeScreenDestination.route) {
                                popUpTo(LoginScreenDestination.route){
                                    inclusive=true
                                }

                            }
                        },
                        onClickToSignUp = {
                            navigator.navigate(SigUpScreenDestination.route){
                                popUpTo(LoginScreenDestination.route){
                                    inclusive=true
                                }
                            }
                        },
                        onForgetPassword = {
                            navigator.navigate(ForgotPasswordScreenDestination.route) {
                                 popUpTo(LoginScreenDestination.route){
                                    inclusive=true
                                }
                            }
                        }
                    )

                }
            }


        }
    }


}




