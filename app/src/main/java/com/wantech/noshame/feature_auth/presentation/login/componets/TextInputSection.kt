package com.wantech.noshame.feature_auth.presentation.login.componets

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wantech.noshame.feature_auth.presentation.login.AButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun TextInPutSection(
    buttonLabel: String,
    onClickLoginButton: () -> Unit,
    onClickToSignUp: () -> Unit,
    onForgetPassword: () -> Unit
) {
    var emailFieldState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }

    val keyBoardController = LocalSoftwareKeyboardController.current

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                InputTextField(
                    textValue = emailFieldState,
                    labelText = "Email",
                    onValueChange = { emailFieldState = it },

                )


                PasswordTextField(
                    textValue = passwordState,
                    labelText = "Password",
                    placeHolder = "Your Password",

                    onValueChange = {
                        passwordState = it
//                        if (passwordState.length == 10) keyBoardController?.hide()
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
                    val checkedT = remember {
                        mutableStateOf(false)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Checkbox(
                        checked = checkedT.value, onCheckedChange = {
                            checkedT.value = it
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = MaterialTheme.colorScheme.surface,
//                    checkmarkColor = Color.Magenta
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Remember Me",
                        style = MaterialTheme.typography.labelSmall
                    )

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
                        )
                    }
                }
                AButton(
                    text = buttonLabel,
                    onClick = onClickLoginButton,
                    modifier = Modifier,
                    buttonEnabled =
                    {
                        passwordState.isNotBlank() &&
                                ((passwordState.length >= 8)
                                        && emailFieldState.isNotBlank()
                                        && Patterns.EMAIL_ADDRESS.matcher(
                                    emailFieldState
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
//
                            .padding(4.dp)
                    )
                }

            }
        }
    }
}

