package com.wantech.noshame.feature_auth.presentation.forgotPassword.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wantech.noshame.feature_auth.presentation.login.AButton
import com.wantech.noshame.feature_auth.presentation.login.componets.InputTextField
import com.wantech.noshame.feature_auth.presentation.signUp.components.IConWithText
import kotlinx.coroutines.launch
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navigator: DestinationsNavigator) {
    var emailState by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },

        ) { paddingValue ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            contentAlignment = Alignment.Center
        ) {
            IConWithText(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "Forgot Password",
                onClick = {
                    navigator.navigateUp()
                })

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.Center),
//                contentColor = MaterialTheme.colors.surface,
//                backgroundColor = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(12.dp),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .align(Alignment.Center)
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Please Enter your Email, \nyou will receive a link to reset your password via email",
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    InputTextField(
                        textValue = emailState,
                        labelText = "Your Email",
                        onValueChange = { email ->
                            emailState = email

                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Send,
                            keyboardType = KeyboardType.Email
                        )
                    )

                    AButton(
                        text = "Send",
                        onClick = {
                            scope.launch {
                                snackBarHostState
                                    .showSnackbar(
                                        message = "Sent, check your email",
                                        actionLabel = "Dismiss",
                                        duration = SnackbarDuration.Long
                                    )
                            }

                            emailState = ""
                        },

                        modifier = Modifier.padding(bottom = 8.dp),
                        buttonEnabled = ({
                            emailState.isNotBlank() &&
                                    android.util.Patterns.EMAIL_ADDRESS.matcher(emailState)
                                        .matches()
                        }
                                )
                    )
                }
            }

        }

    }


}