package com.capstone.appcompose.ui.screen.favourite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.capstone.appcompose.component.FoodCardFav
import com.capstone.appcompose.data.repository.Foods

@Composable
fun FavContent(
    padding: PaddingValues,
    foods: Foods,
    navController: NavController
){
    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp)
    ){
        items(
            items = foods
        ) { food ->
            FoodCardFav(
                foodResponse = food,
                navController = navController)
        }
    }
}