package com.capstone.appcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.navigation.Screen

@Composable
fun FoodCardRec(
    foodResponse: FoodResponse,
    navController: NavController,
){

    Surface{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable {
                    navController.currentBackStackEntry?.savedStateHandle?.set("food", foodResponse)
                    navController.navigate(Screen.Detail.route)
                }
        ){
            Box(modifier = Modifier
                .size(78.dp)
                .clip(RoundedCornerShape(8.dp))){
                Image(
                    painter = painterResource(id = R.drawable.imagenotfound),
                    contentDescription = "foodImage",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column() {
                Text(
                    text = foodResponse.food_name,
                    color = Color.Gray,
                    style = MaterialTheme.typography.h6.copy(fontSize = 12.sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = foodResponse.calories + "kcal",
                    style = MaterialTheme.typography.h6.copy(fontSize = 24.sp),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth(),
            thickness = 1.dp
        )
    }
}