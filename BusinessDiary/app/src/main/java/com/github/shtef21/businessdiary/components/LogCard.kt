package com.github.shtef21.businessdiary.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.shtef21.businessdiary.entities.DiaryLog
import com.github.shtef21.businessdiary.entities.LogFormData
import com.github.shtef21.businessdiary.logic.Routes
import com.github.shtef21.businessdiary.logic.dbAddOrUpdateLog
import com.github.shtef21.businessdiary.ui.theme.LogDesc

@Composable
fun LogCard(
    log: DiaryLog,
    onDeleteClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val arrowIcon = if (isExpanded) Icons.Rounded.KeyboardArrowDown
        else Icons.Rounded.KeyboardArrowRight

    // Card (surface with shadow)
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(0.dp, 10.dp)
        ) {
            LogCardHeader(
                log,
                onExpandToggle = { isExpanded = !isExpanded },
                onTrashCanClick = onDeleteClick,
                headerIcon = arrowIcon
            )
            if (isExpanded) {
                LogCardBody(log)
            }
        }

    }
}

@Composable
fun LogCardHeader(
    log: DiaryLog,
    onExpandToggle: () -> Unit,
    onTrashCanClick: () -> Unit,
    headerIcon: ImageVector
) {
    var confirmDeleteDialogOpen by remember {
        mutableStateOf(false)
    }
    val headerHeight = 80.dp

    // Inner wrapper
    Row(
        modifier = Modifier
            .height(headerHeight)
            .clickable(onClick = onExpandToggle),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Vertical colored line
        Box(
            modifier = Modifier
                .size(4.dp, headerHeight)
                .clip(
                    RoundedCornerShape(
                        0.dp, 8.dp, 8.dp, 0.dp
                    )
                )
                .background(log.difficulty.color)
        )

        // Arrow icon
        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                headerIcon,
                contentDescription = "Card arrow",
                tint = log.difficulty.color,
                modifier = Modifier.size(32.dp)
            )
        }

        // Title and description
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 2.dp, 0.dp, 5.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = log.title,
                    color = log.difficulty.color,
                    fontSize = 20.sp
                )
                Text(
                    text = log.description,
                    color = LogDesc,
                    fontSize = 16.sp
                )
            }
        }

        // Delete icon
        Box(
            modifier = Modifier
                .padding(18.dp, 0.dp)
                .clickable(onClick = {
                    confirmDeleteDialogOpen = true
                }),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                Icons.Rounded.Delete,
                contentDescription = "Delete icon",
                tint = log.difficulty.color,
                modifier = Modifier.size(28.dp)
            )
        }

        if (confirmDeleteDialogOpen) {
            ConfirmationDialog(
                icon = Icons.Default.Warning,
                title = "Confirm deletion",
                body = "Are you sure you want to delete the item titled \"${log.title}\"?",
                onConfirm = {
                    onTrashCanClick()
                    confirmDeleteDialogOpen = false
                },
                onDismiss = { confirmDeleteDialogOpen = false }
            )
        }
    }
}

@Composable
fun LogCardBody(log: DiaryLog) {
    var titleSuffix by remember { mutableStateOf("") }
    var footerMessage by remember { mutableStateOf("") }
    var isReadOnlyForm by remember { mutableStateOf(true) }

    LogForm(
        initialData = LogFormData(
            diaryLogTitle = log.title,
            diaryLogText = log.description,
            difficulty = log.difficulty
        ),
        isReadOnlyForm = isReadOnlyForm,
        formTitle = "Log details$titleSuffix",
        footerMessage,
        buttonText = if (isReadOnlyForm) "Enable edit" else "Submit",
        onSubmit = { formData ->
            if (isReadOnlyForm) {
                // Enable editing
                isReadOnlyForm = !isReadOnlyForm
                titleSuffix = " (edit enabled)"
            }
            else {
                // Send request
                val diaryLog = DiaryLog(
                    formData.diaryLogTitle,
                    formData.diaryLogText,
                    formData.difficulty,
                    logId = log.logId
                )
                footerMessage = "Sending..."
                dbAddOrUpdateLog(
                    log = diaryLog,
                    onSuccess = {
                        footerMessage = "Done."
                    },
                    onDatabaseError = { dbError ->
                        Log.e("-- DB ERROR --", dbError.message)
                        footerMessage = "Error. More info in Logcat."
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewLogCard() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text("Heya")
        Box(modifier = Modifier.padding(5.dp)) {
            LogCard(
                log = DiaryLog("Some title", "Some descr"),
                onDeleteClick = {}
            )
        }
    }
}
