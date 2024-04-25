package com.github.shtef21.businessdiary.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.shtef21.businessdiary.components.LogCard
import com.github.shtef21.businessdiary.logic.DiaryLog
import com.github.shtef21.businessdiary.logic.dbListenForLogChanges
import com.github.shtef21.businessdiary.ui.theme.BlueLog

@Composable
fun DiaryShowContainer() {

    var logs: List<DiaryLog> by remember {
        mutableStateOf(emptyList())
    }
    dbListenForLogChanges ({ logs = it })

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        if (logs.isEmpty()) {
            NoLogsFoundColumn()
        }
        else {
            LogsColumn(logs)
        }
    }
}


@Composable
fun NoLogsFoundColumn() {

    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(50) {
            LogCard(
                "Loading...",
                "Card loading is in progress",
                Color.Red
            )
        }
    }
}

@Composable
fun LogsColumn(logs: List<DiaryLog>) {

    LazyColumn(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(logs) {  log ->
            LogCard(log.title, log.description, BlueLog)
        }
    }
}
