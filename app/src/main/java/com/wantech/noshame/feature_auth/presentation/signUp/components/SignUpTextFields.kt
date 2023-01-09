package com.wantech.noshame.feature_auth.presentation.signUp.components


import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
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
import com.wantech.noshame.feature_auth.presentation.login.ATextButton
import com.wantech.noshame.feature_auth.presentation.login.componets.InputTextField
import com.wantech.noshame.feature_auth.presentation.login.componets.PasswordTextField
import com.wantech.noshame.feature_auth.presentation.signUp.SignUpViewModel


@Composable

fun SignUpTextFields(
    buttonLabel: String,
    toMoreInforScreen: () -> Unit,
    onClickToLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
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
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Text(
//                    text = "Create Account",
//                    style = MaterialTheme.typography.headlineSmall,
//                    textAlign = TextAlign.Center
//                )
                item {
                    Text(
                        text = stringResource(id = R.string.createAccount),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, top = 32.dp),
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.background,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }


                item {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        InputTextField(
                            textValue = state.userName,
                            labelText = "UserName",
                            onValueChange = { viewModel.onEvent(SignupEvent.EnteredUsername(it)) },
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )

                        InputTextField(
                            textValue = state.email,
                            labelText = "Email",
                            onValueChange = { viewModel.onEvent(SignupEvent.EnteredEmail(it)) },
                            modifier = Modifier.weight(0.5f),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )

                        )
                    }
                    PasswordTextField(
                        modifier = Modifier.fillMaxWidth(),
                        textValue = state.password,
                        labelText = "Password",

                        placeHolder = "Your Password",
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredPassword(it))

                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                        ),
                        passwordModifier = Modifier.fillMaxWidth(0.9f)

                    )


                    ATextButton(
                        text = stringResource(id = R.string.next),
                        onClick = toMoreInforScreen,
                        modifier = Modifier.fillMaxWidth(0.6f),
                        buttonEnabled = {
                            state.email.isNotBlank() && state.password.isNotBlank() &&
                                    state.userName.isNotBlank() && state.password.length > 6 && state.userName.length > 3 &&
                                    state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                                state.email
                            ).matches())
                        },
//                        leadingIcon = Icons.Default.ArrowForwardIos

                    )

                    TextButton(
                        onClick = onClickToLogin,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(2.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.already_have_Account),
                            color = MaterialTheme.colorScheme.surface,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(id = R.string.sign_in),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.padding(4.dp),
                        )
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
                            text = stringResource(id = R.string.createAccount),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, top = 32.dp),
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

                            onValueChange = { viewModel.onEvent(SignupEvent.EnteredEmail(it)) },

                            )
                        InputTextField(
                            textValue = state.userName,
                            labelText = "UserName",
                            onValueChange = { viewModel.onEvent(SignupEvent.EnteredUsername(it)) },

                            )
                        PasswordTextField(
                            textValue = state.password,
                            labelText = "Password",
                            placeHolder = "Enter your password",
                            onValueChange = { viewModel.onEvent(SignupEvent.EnteredPassword(it)) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                            ),
                        )

                        ATextButton(
                            text = buttonLabel,
                            onClick = toMoreInforScreen,
                            modifier = Modifier,
                            buttonEnabled = {
                                state.password.isNotBlank() && (state.password.length >= 8) &&
                                        state.userName.isNotBlank() && state.email.isNotBlank()
                                        && Patterns.EMAIL_ADDRESS.matcher(state.email)
                                    .matches()
                            },
                            leadingIcon = Icons.Default.ArrowForwardIos
                        )
                        TextButton(
                            onClick = onClickToLogin,
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(2.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.already_have_Account),
                                color = MaterialTheme.colorScheme.surface,

                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = stringResource(id = R.string.sign_in),
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
