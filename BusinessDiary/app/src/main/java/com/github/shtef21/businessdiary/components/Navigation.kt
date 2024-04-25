package com.github.shtef21.businessdiary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.shtef21.businessdiary.logic.Routes

@Composable
fun Navigation(navController: NavHostController, initialRoute: String) {
    var currentRoute by remember {
        mutableStateOf(initialRoute)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
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