package com.github.shtef21.businessdiary.components

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

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