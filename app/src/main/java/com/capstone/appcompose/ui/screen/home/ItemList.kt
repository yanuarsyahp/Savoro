package com.capstone.appcompose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.model.HomeModel.Item
import com.capstone.appcompose.navigation.Screen
import com.capstone.appcompose.ui.theme.GreenSavoro

@Composable
fun ItemList(
    item: Item,
    navController: NavController,
){
    val context = LocalContext.current
    val dataStore = DataStoreRepository(context)
    val newToken = dataStore.getUserToken.collectAsState(initial = "")
    Card(
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        backgroundColor = GreenSavoro,
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp)
            .shadow(8.dp, shape = MaterialTheme.shapes.large)
            .clickable{
                navController.currentBackStackEntry?.savedStateHandle?.set("item", item)
                when {
                    item.id == "1" -> {
                        navController.navigate(Screen.Kal_1Age.route)
                    }
                    item.id == "2" -> {
                        navController.navigate(Screen.FoodList.route)
                    }
                    item.id == "3" -> {
                        navController.navigate(Screen.Upcoming.route)
                    }
                    item.id == "4" -> {
                        if (newToken.value.isNotEmpty()){
                            navController.navigate(Screen.RecFood.route)
                        }else{
                            navController.navigate(Screen.Unlock.route)
                        }

                    }
                }
            }
    ){
        Row(
            modifier = Modifier.padding(horizontal = 23.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = item.desc,
                style = MaterialTheme.typography.h2.copy(fontSize = 24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Image(
                painter = painterResource(id = item.img),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)

            )

        }
    }
}