package com.github.shtef21.businessdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.shtef21.businessdiary.logic.Routes
import com.github.shtef21.businessdiary.pages.DiaryFormContainer
import com.github.shtef21.businessdiary.pages.DiaryShowContainer
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
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        appWrapper(navController)
                        Navigation(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun appWrapper(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.SHOW_LOGS.toString()
    ) {
        composable(Routes.SHOW_LOGS.toString()) {
            DiaryShowContainer()
        }
        composable(Routes.ADD_LOG.toString()) {
            DiaryFormContainer()
        }
    }

}

@Composable
fun Navigation(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .alpha(0.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Green)
        ) {
        }
        // Buttons content
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color.Blue)
        ) {
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(Routes.SHOW_LOGS.toString())
                },
                icon = { Icon(Icons.Filled.Home, "Home page") },
                text = { Text(text = "Home") },
                modifier = Modifier
                //.offset(x = 30.dp, y = 670.dp)
            )
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(Routes.ADD_LOG.toString())
                },
                icon = { Icon(Icons.Filled.Create, "Add a log") },
                text = { Text(text = "Compose") },
                modifier = Modifier
                //.offset(x = 200.dp, y = 670.dp)
            )
        }
    }
}
