package com.capstone.appcompose.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.viewmodel.FavViewModel
import com.capstone.appcompose.ui.theme.GreenSavoro

@Composable
fun DetailScreen(
    navController: NavController,
    foodResponse: FoodResponse,
    viewModel: FavViewModel = hiltViewModel()
){
    val TAG = FavViewModel::class.simpleName
    var isLoveSelected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp),
    ) {
        Column {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .shadow(
                        shape = RoundedCornerShape(
                            bottomStart = 35.dp,
                            bottomEnd = 35.dp,
                            topStart = 0.dp,
                            topEnd = 0.dp
                        ),
                        elevation = 6.dp,
                    )
            ){
                Image(
                    painter = painterResource(id = R.drawable.imagenotfound),
                    contentDescription = null,
                    modifier = Modifier
                        .height(385.dp)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 35.dp,
                                bottomEnd = 35.dp,
                                topStart = 0.dp,
                                topEnd = 0.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.greenback),
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(14.dp)
                        .size(32.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }
            //BODY
            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = foodResponse.food_name,
                        style = MaterialTheme.typography.h6.copy(fontSize = 32.sp),
                        fontWeight = FontWeight.Bold,
                    )
                    LoveSelectedButton(
                        imageSize = 24.dp,
                        isSelected = isLoveSelected,
                        onClick = {
                            isLoveSelected = !isLoveSelected
                            val foodResponse = FoodResponse(
                                0,
                                foodResponse.calories,
                                foodResponse.carbs,
                                foodResponse.fat,
                                foodResponse.food_id,
                                foodResponse.food_name,
                                foodResponse.protein
                            )
                            try {
                                viewModel.addFood(foodResponse)
                                Log.d(TAG, "Success response: $foodResponse")
                            } catch (e: Exception) {
                                Log.d(TAG, "Can\'t add response: $e")
                            }
                        })
                }
                //GREEN INFO
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GreenBar("Calories", foodResponse.calories)
                    Spacer(modifier = Modifier.width(14.dp))
                    GreenBar("Total Fat", foodResponse.fat)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GreenBar("Total Carb", foodResponse.carbs)
                    Spacer(modifier = Modifier.width(14.dp))
                    GreenBar("Protein", foodResponse.protein)
                }
            }
        }
        //DETAIL INFORMATION
        Column() {
            DetailBar(
                title = "Serving size",
                value = "-"
            )
            DetailBar(title = "Fat", value = foodResponse.fat)
            DetailBar(title = "Protein", value = foodResponse.protein)
            DetailBar(title = "Carbohydrates", value = foodResponse.carbs)
            DetailBar(title = "Potassium", value = "-")
            DetailBar(title = "Sodium", value = "-")
        }
    }
}

@Composable
private fun GreenBar(title: String, value: String){
    Box(modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .width(160.dp)
        .background(GreenSavoro),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = value,
                style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
                color = Color.White
            )
        }
    }
}

@Composable
private fun DetailBar(title: String, value: String){
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = value,
                fontSize = 16.sp,
            )
        }
        Divider(modifier = Modifier
            .fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Black
        )
    }
}

@Composable
fun LoveSelectedButton(
    modifier: Modifier = Modifier,
    imageSize: Dp,
    isSelected: Boolean,
    onClick: () -> Unit,
){
    Box(
        modifier = modifier
            .clickable {
                onClick() },
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = loveIcon(isSelected),
            contentDescription = "Male Button",
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
private fun loveIcon(isSelected: Boolean): Painter{
    return if (isSelected){
        painterResource(R.drawable.love_filled)
    }else{
        painterResource(R.drawable.love_outlined)
    }
}