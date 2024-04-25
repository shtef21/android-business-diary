package com.github.shtef21.businessdiary.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.shtef21.businessdiary.components.LogCard
import com.github.shtef21.businessdiary.entities.DiaryLog
import com.github.shtef21.businessdiary.logic.dbListenForLogChanges
import com.github.shtef21.businessdiary.ui.theme.BlueLog

@Composable
fun DiaryShowContainer() {

    var logs: List<DiaryLog> by remember {
        mutableStateOf(emptyList())
    }
    dbListenForLogChanges ({ logs = it })

    if (logs.isEmpty()) {
        NoLogsFoundColumn()
    }
    else {
        LogsColumn(logs)
    }
}


@Composable
fun NoLogsFoundColumn() {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        LogCard(
            "Loading...",
            "Card loading is in progress",
            Color.Gray
        )
    }
}

@Composable
fun LogsColumn(logs: List<DiaryLog>) {

    LazyColumn(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(logs) {  log ->
            LogCard(log.title, log.description, BlueLog)
        }
    }
}
