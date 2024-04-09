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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.shtef21.businessdiary.logic.Routes
import com.github.shtef21.businessdiary.pages.AddLogContainer
import com.github.shtef21.businessdiary.pages.DiaryShowContainer
import com.github.shtef21.businessdiary.pages.testing_firebaseUI
import com.github.shtef21.businessdiary.ui.theme.AppBackground
import com.github.shtef21.businessdiary.ui.theme.BusinessDiaryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessDiaryTheme {
                val navController: NavHostController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppBackground
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        appWrapper(navController, LocalContext.current)
                        Navigation(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun appWrapper(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = Routes.SHOW_LOGS.toString()
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
    }

}

@Composable
fun Navigation(navController: NavHostController) {
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
                NavButton(navController, Routes.TESTING, Icons.Filled.Build, "Search", false)
                NavButton(navController, Routes.SHOW_LOGS, Icons.Filled.Home, "Home", true)
                NavButton(navController, Routes.ADD_LOG, Icons.Filled.Create, "Compose", false)
            }
        }
    }
}

@Composable
fun NavButton(navController: NavHostController, route: Routes, iconVector: ImageVector, iconText: String, isOpen: Boolean) {

    if (isOpen) {
        ExtendedFloatingActionButton(
            onClick = {
                navController.navigate(route.toString())
            },
            icon = { Icon(iconVector, iconText) },
            text = { Text(iconText) }
        )
    }
    else {
        FloatingActionButton(
            onClick = {
                navController.navigate(route.toString())
            }
        ) {
            Icon(iconVector, iconText)
        }
    }
}
