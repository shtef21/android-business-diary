package com.github.shtef21.businessdiary.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.shtef21.businessdiary.logic.DiaryLog
import com.github.shtef21.businessdiary.logic.addItemToDatabase
import java.time.LocalDateTime
import java.util.Date

@Composable
fun DiaryContainer() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DiaryForm()
        }
        DiaryLogAddButton()
    }
}

@Composable
fun DiaryForm() {
    var message by remember { mutableStateOf("") }
    var addEnabled by remember { mutableStateOf(false) }
    var formMessage by remember {mutableStateOf("")}

    var diaryLog by remember {
        mutableStateOf(
            DiaryLog(
                "test",
                "descr"
            )
        )
    }
    val checkValidity = {
        addEnabled =
            diaryLog.title.length > 0 &&
            diaryLog.description.length > 0
    }
    checkValidity()

    TextField(
        value = diaryLog.title,
        onValueChange = {
            diaryLog.title = it
            checkValidity()
        },
        label = { Text("Title")}
    )
    TextField(
        value = diaryLog.description,
        onValueChange = {
            diaryLog.description = it
            checkValidity()
        },
        label = { Text("Description")}
    )
    Button(
        onClick = {
            addItemToDatabase(diaryLog)
            formMessage = "Done."
        },
        enabled = addEnabled
    ) {
        Text(text = "Add")
    }

    if (formMessage.length > 0) {
        Text(formMessage)
    }
}

@Composable
fun DiaryLogAddButton() {
    ExtendedFloatingActionButton(
        onClick = {
            /* TODO */
        },
        icon = { Icon(Icons.Filled.Create, "Add a log") },
        text = { Text(text = "Compose") },
        modifier = Modifier
            .offset(x = 100.dp, y = 320.dp)
    )
}
