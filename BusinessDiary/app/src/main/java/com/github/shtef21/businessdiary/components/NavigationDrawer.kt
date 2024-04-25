package com.github.shtef21.businessdiary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(drawerState: DrawerValue, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */ }
        },
    ) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer", color = Color.Black) },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            Box(
                modifier = Modifier.padding(contentPadding)
            ) {
                content()
            }
        }
    }
}


@Preview
@Composable
fun CheckNav() {

}

@Preview
@Composable
fun PreviewNavigationDrawer() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 1st element takes whatever height it needs
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "First Element")
            Spacer(modifier = Modifier.weight(1f))
        }

        // 2nd element takes max height it can get (scrollable)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .background(Color.Blue)
                .verticalScroll(rememberScrollState())
        ) {
        }

        // 3rd element takes what it can get (it has fixed height)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .shadow(4.dp),
        ) {
            Text("Test")
        }
    }
}
