@file:OptIn(ExperimentalMaterial3Api::class)

package com.wantech.noshame.myths.presentation

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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Menstrual Myths", textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    scrollBehavior = scrollBehavior,
                )
            }) { paddingValues: PaddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(connection = scrollBehavior.nestedScrollConnection)
            ) {

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(240.dp),
                    contentPadding = paddingValues
                ) {
                    items(myths, key = Myth::myth) { item: Myth ->
                        CardWithToggleableDescription(
                            title = item.myth,
                            description = item.fact
                        )
                    }
                }
            }

        }
    }
}