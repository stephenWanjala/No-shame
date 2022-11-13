package com.wantech.noshame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.CustomProgressIndicator
import com.wantech.noshame.ui.theme.NoShameTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoShameTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                val navController = rememberNavController()
//                    NavigationHost(navController = navController)
//                    HomeScreen(navController = navController)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    var indicatorValue by remember {
                        mutableStateOf(0)
                    }
                    CustomProgressIndicator(
                        modifier = Modifier,
                        indicatorValue = indicatorValue
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(

                        value = indicatorValue.toString(),
                        onValueChange = {
                            indicatorValue = if (it.isNotEmpty()) it.toInt() else 0
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    )
                }

            }
        }
    }
}
//}
