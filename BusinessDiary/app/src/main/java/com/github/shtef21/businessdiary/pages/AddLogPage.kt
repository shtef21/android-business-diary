package com.github.shtef21.businessdiary.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.shtef21.businessdiary.components.FormDropdown
import com.github.shtef21.businessdiary.entities.DiaryLog
import com.github.shtef21.businessdiary.entities.Difficulty
import com.github.shtef21.businessdiary.logic.Routes
import com.github.shtef21.businessdiary.logic.dbAddOrUpdateLog

@Composable
fun AddLogContainer(navController: NavController) {
    var addEnabled by remember { mutableStateOf(false) }
    var formMessage by remember { mutableStateOf("") }

    var diaryLogTitle by remember { mutableStateOf("Some title") }
    var diaryLogText by remember { mutableStateOf("some Body") }
    var difficulty by remember { mutableStateOf(Difficulty.Unset) }

    val checkValidity = {
        addEnabled =
            diaryLogTitle.length > 0
                    && diaryLogText.length > 0
    }
    checkValidity()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add a log",
            fontSize = 24.sp, // Adjust the font size as needed
            fontWeight = FontWeight.Bold, // Bold font weight for title
            color = Color.Black, // Title color
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp) // Add padding for spacing
        )

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
        FormDropdown(
            selectedValue = difficulty.toString(),
            options = listOf(
                Difficulty.Unset,
                Difficulty.Easy,
                Difficulty.Normal,
                Difficulty.Difficult
            ),
            label = "Difficulty",
            onChange = { newDifficulty ->
                Log.e("new val", newDifficulty.toString())
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                val diaryLog = DiaryLog(diaryLogTitle, diaryLogText,)
                formMessage = "Sending..."
                dbAddOrUpdateLog(
                    log = diaryLog,
                    onSuccess = {
                        navController.navigate(Routes.SHOW_LOGS.toString())
                    },
                    onDatabaseError = { dbError ->
                        Log.e("-- DB ERROR --", dbError.message)
                        formMessage = "Error. More info in Logcat."
                    }
                )
            },
            enabled = addEnabled
        ) {
            Text(text = "Add")
        }

        if (formMessage.isNotEmpty()) {
            Text(formMessage)
        }
    }
}

