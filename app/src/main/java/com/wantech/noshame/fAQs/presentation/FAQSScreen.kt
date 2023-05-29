package com.wantech.noshame.fAQs.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wantech.noshame.fAQs.data.dataSource.FAQ
import com.wantech.noshame.fAQs.data.dataSource.FAQS
import com.wantech.noshame.fAQs.presentation.components.CardWithToggleableDescription

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FAQSScreen() {
    val faqs = rememberSaveable {
        mutableStateOf(FAQS.faqs)
    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold { paddingValues ->
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "FAQS", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(240.dp),
                    contentPadding = paddingValues
                ) {
                    items(faqs.value) { item: FAQ ->
                        CardWithToggleableDescription(
                            title = item.question,
                            description = item.answer
                        )
                    }

                }
            }

        }
    }
}