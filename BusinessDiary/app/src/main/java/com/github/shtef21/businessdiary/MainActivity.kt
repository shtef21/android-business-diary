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
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Navigation(navController)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            appWrapper(navController)
                        }
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
        startDestination = Routes.ADD_LOG.toString()
    ) {
        composable(Routes.ADD_LOG.toString()) {
            DiaryFormContainer()
        }
        composable(Routes.SHOW_LOGS.toString()) {
            DiaryShowContainer()
        }
    }

}

@Composable
fun Navigation(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Green)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(all = 16.dp)
            ) {
                Text(text = "Card 1")
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(all = 16.dp)
            ) {
                Text(text = "Card 2")
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(all = 16.dp)
            ) {
                Text(text = "Card 3")
            }
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
        /*ExtendedFloatingActionButton(
            onClick = {
                navController.navigate(Routes.SHOW_LOGS.toString())
            },
            icon = { Icon(Icons.Filled.Home, "Home page") },
            text = { Text(text = "Home") },
            modifier = Modifier
            //.offset(x = 30.dp, y = 670.dp)
        )
         */
    }
}
