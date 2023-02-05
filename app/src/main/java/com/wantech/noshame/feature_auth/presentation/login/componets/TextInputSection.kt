package com.wantech.noshame.feature_auth.presentation.login.componets

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.noshame.R
import com.wantech.noshame.feature_auth.presentation.login.AButton
import com.wantech.noshame.feature_auth.presentation.login.LogInViewModel
import com.wantech.noshame.feature_auth.presentation.login.LoginEvent

@Composable

fun TextInPutSection(
    buttonLabel: String,
    onClickLoginButton: () -> Unit,
    onClickToSignUp: () -> Unit,
    onForgetPassword: () -> Unit,
    viewModel: LogInViewModel = hiltViewModel()
) {


    val state = viewModel.state.value
    val configuration = LocalConfiguration.current
    var orientation by remember {
        mutableStateOf(Configuration.ORIENTATION_PORTRAIT)
    }

    LaunchedEffect(key1 = configuration) {
        snapshotFlow { configuration.orientation }.collect { orientation = it }
    }
    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, top = 32.dp)
                                .align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.sign_in_welcome_text),
                            fontWeight = FontWeight.ExtraBold,
                            fontStyle = FontStyle.Normal,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.background,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            InputTextField(
                                textValue = state.email,
                                labelText = "Email",
                                onValueChange = { viewModel.onEvent(LoginEvent.EnteredEmail(it)) },
                                modifier = Modifier.weight(0.5f),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next
                                )

                            )


                            PasswordTextField(
                                modifier = Modifier.weight(0.5f),
                                textValue = state.password,
                                labelText = "Password",

                                placeHolder = "Your Password",
                                onValueChange = {
                                    viewModel.onEvent(LoginEvent.EnteredPassword(it))

                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                                )

                            )

                        }

                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(
                            onClick = onForgetPassword,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(end = 64.dp)
                                .align(Alignment.End),
                            contentPadding = PaddingValues(1.dp),
                        ) {
                            Text(
                                text = stringResource(id = R.string.forgot_password),
                                color = MaterialTheme.colorScheme.surface,
                                style = MaterialTheme.typography.labelSmall,
                                textAlign = TextAlign.Center
                            )
                        }

                        AButton(text = stringResource(id = R.string.sign_in),
                            onClick = {
                                onClickLoginButton()

                            },
                            modifier = Modifier.fillMaxWidth(0.6f),
                            buttonEnabled = {

                                state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                                    state.email
                                ).matches())
                            }

                        )

                        TextButton(
                            onClick = { onClickToSignUp() },
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(2.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.dont_Have_account),
                                color = MaterialTheme.colorScheme.surface,

                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = stringResource(id = R.string.createAccount),
                                color = MaterialTheme.colorScheme.surface,
                                modifier = Modifier.padding(4.dp)
                            )
                        }

                    }
                }
            }


        }
        else -> {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, top = 32.dp)
                                .align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.sign_in_welcome_text),
                            fontWeight = FontWeight.ExtraBold,
                            fontStyle = FontStyle.Normal,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.background,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        InputTextField(
                            textValue = state.email,
                            labelText = "Email",
                            onValueChange = { viewModel.onEvent(LoginEvent.EnteredEmail(it)) },

                            )


                        PasswordTextField(
                            textValue = state.password,
                            labelText = "Password",
                            placeHolder = "Your Password",

                            onValueChange = {
                                viewModel.onEvent(LoginEvent.EnteredPassword(it))
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                            ),

                            )

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {

                            Spacer(modifier = Modifier.width(8.dp))

                            Spacer(modifier = Modifier.width(8.dp))
                            TextButton(
                                onClick = onForgetPassword,
                                modifier = Modifier.wrapContentHeight(),
                                contentPadding = PaddingValues(1.dp),
                            ) {
                                Text(
                                    text = "Forgot password?",
                                    color = MaterialTheme.colorScheme.surface,
                                    style = MaterialTheme.typography.labelSmall,
                                    textAlign = TextAlign.End
                                )
                            }
                        }
                        AButton(
                            text = buttonLabel,
                            onClick = onClickLoginButton,
                            modifier = Modifier,
                            buttonEnabled =
                            {
                                state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                                    state.email
                                ).matches())
                            }

                        )

                        TextButton(
                            onClick = onClickToSignUp,
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(2.dp)
                        ) {
                            Text(
                                text = "Don't Have Account?",
                                color = MaterialTheme.colorScheme.surface,

                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Sign Up",
                                color = MaterialTheme.colorScheme.surface,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                        }

                    }
                }
            }
        }
    }


}

