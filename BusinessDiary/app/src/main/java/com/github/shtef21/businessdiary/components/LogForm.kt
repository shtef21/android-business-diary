package com.github.shtef21.businessdiary.components

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
import com.github.shtef21.businessdiary.entities.Difficulty
import com.github.shtef21.businessdiary.entities.LogFormData

@Composable
fun LogForm(
    initialData: LogFormData,
    isReadOnlyForm: Boolean,
    formTitle: String,
    footerMessage: String,
    buttonText: String,
    onSubmit: (LogFormData) -> Unit
) {
    var addEnabled by remember { mutableStateOf(false) }
    var diaryLogTitle by remember { mutableStateOf(initialData.diaryLogTitle) }
    var diaryLogText by remember { mutableStateOf(initialData.diaryLogText) }
    var diaryLogDifficulty by remember { mutableStateOf(initialData.difficulty) }

    val checkValidity = {
        addEnabled = diaryLogTitle.isNotEmpty() && diaryLogText.isNotEmpty()
    }
    checkValidity()

    Column(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
            .fillMaxSize()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formTitle,
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
            label = { Text("Title")},
            readOnly = isReadOnlyForm
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = diaryLogText,
            onValueChange = {
                diaryLogText = it
                checkValidity()
            },
            label = { Text("Description")},
            readOnly = isReadOnlyForm
        )
        Spacer(modifier = Modifier.height(12.dp))
        FormDropdown(
            selectedValue = diaryLogDifficulty.toString(),
            options = listOf(
                Difficulty.Unset,
                Difficulty.Easy,
                Difficulty.Normal,
                Difficulty.Difficult
            ),
            label = "Difficulty",
            isReadOnly = isReadOnlyForm,
            onChange = { newDifficulty ->
                diaryLogDifficulty = newDifficulty
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                val formData = LogFormData(
                    diaryLogTitle,
                    diaryLogText,
                    diaryLogDifficulty
                )
                onSubmit(formData)
            },
            enabled = addEnabled
        ) {
            Text(buttonText)
        }

        if (footerMessage.isNotEmpty()) {
            Text(footerMessage)
        }
    }
}

