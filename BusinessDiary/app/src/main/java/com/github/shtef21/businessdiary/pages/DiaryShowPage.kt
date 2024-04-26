package com.github.shtef21.businessdiary.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.github.shtef21.businessdiary.components.LogCard
import com.github.shtef21.businessdiary.entities.DiaryLog
import com.github.shtef21.businessdiary.entities.Difficulty
import com.github.shtef21.businessdiary.logic.dbAddOrUpdateLog
import com.github.shtef21.businessdiary.logic.dbDeleteLog
import com.github.shtef21.businessdiary.logic.dbListenForLogChanges

@Composable
fun DiaryShowContainer() {

    var logs: List<DiaryLog> by remember { mutableStateOf(emptyList()) }
    var isInitialLoad by remember { mutableStateOf(true) }
    dbListenForLogChanges ({
        logs = it
        isInitialLoad = false
    })

    if (logs.isEmpty() && isInitialLoad) {
        NoLogsFoundColumn()
    }
    else {
        LogsColumn(
            logs,
            onDelete = {
                dbDeleteLog(
                    it.logId,
                    onSuccess = {},
                    onDatabaseError = { dbError ->
                        Log.e("-- DB ERROR while deleting item --", dbError.message)
                    }
                )
            }
        )
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
            DiaryLog(
                "Loading",
                "Waiting on data...",
                Difficulty.Unknown
            ),
            onDeleteClick = {}
        )
    }
}

@Composable
fun LogsColumn(logs: List<DiaryLog>, onDelete: (DiaryLog) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(logs) {  logItem: DiaryLog ->
            LogCard(
                logItem,
                onDeleteClick = { onDelete(logItem) }
            )
        }
    }
}
