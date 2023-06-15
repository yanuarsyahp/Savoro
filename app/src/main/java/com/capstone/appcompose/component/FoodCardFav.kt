package com.capstone.appcompose.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.navigation.Screen
import com.capstone.appcompose.ui.theme.RedSavoro
import com.capstone.appcompose.viewmodel.FavViewModel
import androidx.compose.material3.AlertDialog
import com.capstone.appcompose.R
import com.capstone.appcompose.ui.theme.GreenSavoro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodCardFav(
    foodResponse: FoodResponse,
    navController: NavController,
    viewModel: FavViewModel = hiltViewModel()
){
    var showDialog by remember{ mutableStateOf(false) }
    Surface{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable {
                    navController.currentBackStackEntry?.savedStateHandle?.set("food", foodResponse)
                    navController.navigate(Screen.Detail.route)
                }
        ){
            Row{
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
                        text = foodResponse.calories,
                        style = MaterialTheme.typography.h6.copy(fontSize = 24.sp),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.Delete,
                tint = RedSavoro,
                contentDescription = "Delete food",
                modifier = Modifier.clickable {
                    showDialog = true
                }
            )
            if(showDialog){
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Remove favorite foods", fontWeight = FontWeight.Bold) },
                    text = { Text(text = "Are you sure you want to remove this food from favorites?") },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false},
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                backgroundColor = GreenSavoro
                            )
                        ) {
                            Text(text = "Cancel")
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.deleteFood(foodResponse)
                            showDialog = false
                        }) {
                            Text(text = "Confirm", color = GreenSavoro)
                        }
                    }
                )
            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth(),
            thickness = 1.dp
        )
    }
}