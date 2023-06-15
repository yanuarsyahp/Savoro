package com.capstone.appcompose.ui.screen.recommend

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.component.FoodCardRec
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.ui.theme.GreenSavoro
import com.capstone.appcompose.viewmodel.FoodViewModel

@Composable
fun RecommendScreen(
    navController: NavController,
    viewModel: FoodViewModel,
){
    val context = LocalContext.current
    val dataStore = DataStoreRepository(context)
    val recFood = dataStore.getRecFood.collectAsState(initial = "")

    val foodList by viewModel.RecFoods.observeAsState(emptyList())

    LaunchedEffect(Unit){
        viewModel.getRecommendation(recFood.value)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
    ){
        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp)
        ){
            item{
                Row(
                    modifier = Modifier
                        .background(GreenSavoro)
                        .padding(24.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                    Text(
                        text = "Food Recommendation",
                        style = MaterialTheme.typography.h2.copy(fontSize = 20.sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = null,
                        tint = GreenSavoro
                    )
                }
            }
            items(foodList.take(4)){food: FoodResponse ->
                FoodCardRec(food, navController )
            }
        }
    }
}