package com.github.shtef21.businessdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.shtef21.businessdiary.components.Navigation
import com.github.shtef21.businessdiary.logic.DiaryLog
import com.github.shtef21.businessdiary.logic.Routes
import com.github.shtef21.businessdiary.pages.AddLogContainer
import com.github.shtef21.businessdiary.pages.DiaryShowContainer
import com.github.shtef21.businessdiary.pages.LogDetail
import com.github.shtef21.businessdiary.pages.testing_firebaseUI
import com.github.shtef21.businessdiary.ui.theme.AppBackground
import com.github.shtef21.businessdiary.ui.theme.BusinessDiaryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessDiaryTheme {
                val navController: NavHostController = rememberNavController()
                val initialRoute: String = Routes.SHOW_LOGS.toString()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppBackground)
                ) {
                    Navigation(navController, initialRoute)
                    PageWrapper(navController, initialRoute)
                }
            }
        }
    }
}

@Composable
fun PageWrapper(navController: NavHostController, initialRoute: String) {
    NavHost(
        navController = navController,
        startDestination = initialRoute
    ) {
        composable(Routes.ADD_LOG.toString()) {
            AddLogContainer(navController)
        }
        composable(Routes.SHOW_LOGS.toString()) {
            DiaryShowContainer()
        }
        composable(Routes.TESTING.toString()) {
            testing_firebaseUI()
        }
        composable(Routes.LOG_DETAILS.toString()) {
            LogDetail(DiaryLog("", ""))
        }
    }
}

