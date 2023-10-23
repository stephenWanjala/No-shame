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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.wantech.noshame.fAQs.data.dataSource.FAQ
import com.wantech.noshame.fAQs.data.dataSource.FAQS
import com.wantech.noshame.fAQs.presentation.components.CardWithToggleableDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun FAQSScreen() {
    val faqs = rememberSaveable {
        mutableStateOf(FAQS.faqs)
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "FAQS", textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    scrollBehavior = scrollBehavior,
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)) {

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