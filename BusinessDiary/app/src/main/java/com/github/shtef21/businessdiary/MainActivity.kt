package com.github.shtef21.businessdiary

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppBackground
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        AppWrapper(navController, initialRoute, LocalContext.current)
                        Navigation(navController, initialRoute)
                    }
                }
            }
        }
    }
}

@Composable
fun AppWrapper(navController: NavHostController, initialRoute: String, context: Context) {
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
            testing_firebaseUI(context)
        }
        composable(Routes.LOG_DETAILS.toString()) {
            LogDetail(DiaryLog("", ""))
        }
    }
    // asd
}

@Composable
fun Navigation(navController: NavHostController, initialRoute: String) {
    var currentRoute by remember {
        mutableStateOf(initialRoute)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 8.dp, 5.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                NavButton(
                    Icons.Filled.Settings,
                    "DEV PAGE",
                    Routes.TESTING.toString(),
                    currentRoute
                ) {
                    currentRoute = Routes.TESTING.toString()
                    navController.navigate(currentRoute)
                }
                NavButton(
                    Icons.Filled.Create,
                    "Details",
                    Routes.LOG_DETAILS.toString(),
                    currentRoute
                ) {
                    currentRoute = Routes.LOG_DETAILS.toString()
                    navController.navigate(currentRoute)
                }
                NavButton(
                    Icons.Filled.Home,
                    "Home",
                    Routes.SHOW_LOGS.toString(),
                    currentRoute
                ) {
                    currentRoute = Routes.SHOW_LOGS.toString()
                    navController.navigate(currentRoute)
                }
                NavButton(
                    Icons.Filled.Add,
                    "Compose",
                    Routes.ADD_LOG.toString(),
                    currentRoute
                ) {
                    currentRoute = Routes.ADD_LOG.toString()
                    navController.navigate(currentRoute)
                }
            }
        }
    }
}

@Composable
fun NavButton(
    iconVector: ImageVector,
    iconText: String,
    navRoute: String,
    currAppRoute: String,
    onNavClick: () -> Unit = {}
) {
    val isCurrentlyOpen = navRoute == currAppRoute

    if (isCurrentlyOpen) {
        ExtendedFloatingActionButton(
            onClick = onNavClick,
            icon = { Icon(iconVector, iconText) },
            text = { Text(iconText) }
        )
    }
    else {
        FloatingActionButton(onClick = onNavClick) {
            Icon(iconVector, iconText)
        }
    }
}
