@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.wantech.noshame.myths.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.wantech.noshame.fAQs.presentation.components.CardWithToggleableDescription
import com.wantech.noshame.myths.data.datasource.MYTHS
import com.wantech.noshame.myths.domain.model.Myth
@Destination
@Composable
fun MenstrualMythsScreen() {
    val myths by rememberSaveable {
        mutableStateOf(MYTHS.myths)
    }
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues: PaddingValues ->
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Menstrual Myths", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(240.dp),
                    contentPadding = paddingValues
                ) {
                    items(myths) { item: Myth ->
                        CardWithToggleableDescription(title = item.myth, description = item.fact)

                    }
                }
            }

        }
    }
}