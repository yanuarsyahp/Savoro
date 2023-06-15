package com.capstone.appcompose.model.HomeModel

import androidx.compose.ui.graphics.vector.ImageVector
import com.capstone.appcompose.navigation.Screen

data class NavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
