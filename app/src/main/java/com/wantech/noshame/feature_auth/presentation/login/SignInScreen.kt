package com.wantech.noshame.feature_auth.presentation.login


import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wantech.noshame.R
import com.wantech.noshame.core.presentation.components.LoadingDialog
import com.wantech.noshame.destinations.ForgotPasswordScreenDestination
import com.wantech.noshame.destinations.HomeScreenDestination
import com.wantech.noshame.destinations.LoginScreenDestination
import com.wantech.noshame.destinations.SigUpScreenDestination
import com.wantech.noshame.feature_auth.presentation.login.componets.TextInPutSection


@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LogInViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state) {
        state.value.login?.let {
            navigator.navigate(HomeScreenDestination) {
                popUpTo(LoginScreenDestination) {
                    inclusive = true
                }

            }
        }
    }
    val context = LocalContext.current
    val prefs =context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    val token = prefs.getString("token", null)
    if (token != null) {
        navigator.navigate(HomeScreenDestination) {
            popUpTo(LoginScreenDestination) {
                inclusive = true
            }
        }
    }
    LaunchedEffect(state.value.error) {
        state.value.error?.let { uiText ->
            snackbarHostState.showSnackbar(
                message = uiText.asString(context),
                actionLabel = "Dismiss",
                duration = SnackbarDuration.Short
            )
        }
    }

    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
//            contentAlignment = Alignment.Center
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .align(Alignment.Center),
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
                            onClickToSignUp = {
                                navigator.navigate(SigUpScreenDestination) {
                                    popUpTo(LoginScreenDestination) {
                                        inclusive = true
                                    }
                                }
                            },
                            onForgetPassword = {
                                navigator.navigate(ForgotPasswordScreenDestination) {
                                    popUpTo(LoginScreenDestination) {
                                        inclusive = true
                                    }
                                }
                            },
                            viewModel = viewModel,
                            onClickLogin = { viewModel.onEvent(LoginEvent.Login) }
                        )

                    }
                }
                AnimatedVisibility(
                    visible = (state.value.isLoading), modifier = Modifier.align(
                        Alignment.Center
                    )
                ) {
                    LoadingDialog()

                }
            }
        }
    }
}




