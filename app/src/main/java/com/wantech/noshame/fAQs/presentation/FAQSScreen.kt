package com.wantech.noshame.fAQs.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wantech.noshame.fAQs.data.dataSource.FAQ
import com.wantech.noshame.fAQs.data.dataSource.FAQS
import com.wantech.noshame.fAQs.presentation.components.CardWithToggleableDescription

@Composable
fun FAQSScreen(navController: NavHostController) {
    val faqs = remember {
        mutableStateOf(FAQS.faqs)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)) {
            items(faqs.value) { item: FAQ ->
                CardWithToggleableDescription(title = item.question, description = item.answer)

            }

        }
    }
}