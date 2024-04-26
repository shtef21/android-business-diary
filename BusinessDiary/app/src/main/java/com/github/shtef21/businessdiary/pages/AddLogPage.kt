package com.github.shtef21.businessdiary.pages

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.github.shtef21.businessdiary.components.LogForm
import com.github.shtef21.businessdiary.entities.DiaryLog
import com.github.shtef21.businessdiary.entities.Difficulty
import com.github.shtef21.businessdiary.entities.LogFormData
import com.github.shtef21.businessdiary.logic.Routes
import com.github.shtef21.businessdiary.logic.dbAddOrUpdateLog

@Composable
fun AddLogContainer(navController: NavController) {
    var footerMessage by remember { mutableStateOf("") }

    LogForm(
        initialData = LogFormData(
            diaryLogTitle = "",
            diaryLogText = "",
            difficulty = Difficulty.Unset
        ),
        isReadOnlyForm = false,
        formTitle = "Add a log",
        footerMessage,
        buttonText = "Add",
        onSubmit = { formData ->

            val diaryLog = DiaryLog(
                formData.diaryLogTitle,
                formData.diaryLogText,
                formData.difficulty
            )
            footerMessage = "Sending..."
            dbAddOrUpdateLog(
                log = diaryLog,
                onSuccess = {
                    footerMessage = "Done. Rerouting..."
                    navController.navigate(Routes.SHOW_LOGS.toString())
                },
                onDatabaseError = { dbError ->
                    Log.e("-- DB ERROR --", dbError.message)
                    footerMessage = "Error. More info in Logcat."
                }
            )
        }
    )
}
