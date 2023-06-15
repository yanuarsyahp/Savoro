package com.capstone.appcompose.ui.screen.knowledge

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.ui.theme.GreenSavoro

@Composable
fun KnowledgeScreen(
    navController: NavController
){
    AppBar(modifier = Modifier, navController = navController)
    Column(modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth()
    ){

    }
}

@Composable
private fun AppBar(
    navController: NavController,
    modifier: Modifier
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(GreenSavoro)
    ){
        Row(
            modifier = Modifier
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
                text = "Nutrition Knowledge Base",
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
}