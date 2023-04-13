package com.wantech.noshame.fAQs.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.wantech.noshame.fAQs.data.dataSource.FAQ
import com.wantech.noshame.fAQs.data.dataSource.FAQS
import com.wantech.noshame.fAQs.presentation.components.CardWithToggleableDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQSScreen() {
    val faqs = remember {
        mutableStateOf(FAQS.faqs)
    }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar (title = {
                Text(text = "FAQs")
            })
        }
            ){ paddingValues ->
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = paddingValues) {
                items(faqs.value) { item: FAQ ->
                    CardWithToggleableDescription(title = item.question, description = item.answer)

                }

            }
        }

    }
}