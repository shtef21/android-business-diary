package com.github.shtef21.businessdiary.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.shtef21.businessdiary.logic.DiaryLog
import com.github.shtef21.businessdiary.logic.addDiaryLogToDatabase

@Composable
fun DiaryFormContainer() {
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
    }
}

@Composable
fun DiaryForm() {
    var addEnabled by remember { mutableStateOf(false) }
    var formMessage by remember { mutableStateOf("") }

    var diaryLogTitle by remember { mutableStateOf("") }
    var diaryLogText by remember { mutableStateOf("") }
    val checkValidity = {
        addEnabled =
            diaryLogTitle.length > 0
            && diaryLogText.length > 0
    }
    checkValidity()

    diaryLogTitle = "Test title"
    diaryLogText = "Test body"
    checkValidity()

    TextField(
        value = diaryLogTitle,
        onValueChange = {
            diaryLogTitle = it
            checkValidity()
        },
        label = { Text("Title")}
    )
    Spacer(modifier = Modifier.height(12.dp))
    TextField(
        value = diaryLogText,
        onValueChange = {
            diaryLogText = it
            checkValidity()
        },
        label = { Text("Description")}
    )
    Spacer(modifier = Modifier.height(12.dp))
    Button(
        onClick = {
            val diaryLog = DiaryLog(
                diaryLogTitle,
                diaryLogText,
            )
            addDiaryLogToDatabase(diaryLog)
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
