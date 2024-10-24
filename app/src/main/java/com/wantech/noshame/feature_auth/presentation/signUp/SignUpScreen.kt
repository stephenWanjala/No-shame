package com.wantech.noshame.feature_auth.presentation.signUp

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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wantech.noshame.R
import com.wantech.noshame.destinations.LoginScreenDestination
import com.wantech.noshame.destinations.MoreInformationScreenDestination
import com.wantech.noshame.destinations.SigUpScreenDestination
import com.wantech.noshame.feature_auth.presentation.signUp.components.SignUpTextFields

@Destination
@Composable
fun SigUpScreen(navigator: DestinationsNavigator) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

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
                    .fillMaxWidth(),
//                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                SignUpTextFields(buttonLabel = stringResource(id = R.string.next),
                    toMoreInforScreen = {authDetails->
                        navigator.navigate(MoreInformationScreenDestination.invoke(authDetails)) {
                            popUpTo(SigUpScreenDestination) {
                                inclusive = true
                            }
                        }
                    },
                    onClickToLogin = {
                        navigator.navigate(LoginScreenDestination) {
                            popUpTo(SigUpScreenDestination) {
                                inclusive = true
                            }
                        }
                    })
            }

        }
    }
}








