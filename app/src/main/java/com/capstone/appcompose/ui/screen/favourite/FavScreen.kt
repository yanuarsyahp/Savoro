package com.capstone.appcompose.ui.screen.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.capstone.appcompose.viewmodel.FavViewModel

@Composable
fun FavScreen(
    navController: NavController,
    viewModel: FavViewModel = hiltViewModel()
){
    val foods by viewModel.foods.collectAsState(
        initial = emptyList()
    )
    Column() {
        Text(
            text = "Favorite",
            style = MaterialTheme.typography.h2.copy(fontSize = 32.sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        )
        Scaffold(
            content = {padding ->
                FavContent(
                    padding = padding,
                    foods = foods,
                    navController = navController
                )
            }
        )
    }

}