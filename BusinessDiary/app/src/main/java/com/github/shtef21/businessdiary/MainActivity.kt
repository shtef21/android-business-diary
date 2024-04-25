package com.github.shtef21.businessdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.shtef21.businessdiary.components.Navigation
import com.github.shtef21.businessdiary.entities.DiaryLog
import com.github.shtef21.businessdiary.logic.Routes
import com.github.shtef21.businessdiary.pages.AddLogContainer
import com.github.shtef21.businessdiary.pages.DiaryShowContainer
import com.github.shtef21.businessdiary.pages.LogDetail
import com.github.shtef21.businessdiary.pages.testing_firebaseUI
import com.github.shtef21.businessdiary.ui.theme.BusinessDiaryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessDiaryTheme {
                AppWrapper()
            }
        }
    }
}

@Composable
fun AppWrapper() {
    val navController: NavHostController = rememberNavController()
    val initialRoute: String = Routes.SHOW_LOGS.toString()

    Column(Modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp),
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "\uD83D\uDCDD Business diary",
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp, // 3rem in sp
                        letterSpacing = (-0.02).em // -0.02em in em
                    )
                )
            }
        }

        // TODO: Fix inifite height on scrollable component (with smth other than height)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
                .height(100.dp)
        ) {
            PageRouter(navController, initialRoute)
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp),
        ) {
            Navigation(navController, initialRoute)
        }
    }
}

@Composable
fun PageRouter(navController: NavHostController, initialRoute: String) {
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

