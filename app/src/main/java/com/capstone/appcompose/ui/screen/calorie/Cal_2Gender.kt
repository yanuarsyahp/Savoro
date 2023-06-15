package com.capstone.appcompose.ui.screen.calorie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.ui.theme.GreenSavoro

@Composable
fun Cal_2Gender(
    navController: NavController,
    age: String?
){
    var isMaleSelected by remember { mutableStateOf(false) }
    var isFemaleSelected by remember { mutableStateOf(false) }

    var newAge = age
    var newGender by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(GreenSavoro)
    )
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
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
        Spacer(modifier = Modifier.height(22.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.icon_kcal_outlined),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
            Spacer(modifier = Modifier.height(175.dp))
            Text(
                text = "Choose your gender",
                style = MaterialTheme.typography.h2.copy(fontSize = 24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MaleSelectedButton(
                        modifier = Modifier,
                        imageSize = 100.dp,
                        isSelected = isMaleSelected,
                        onClick = {
                            isMaleSelected = !isMaleSelected
                            isFemaleSelected = false
                            newGender = "male"
                        }
                    )
                    Text(
                        text = "Male",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(36.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    FemaleSelectedButton(
                        modifier = Modifier,
                        imageSize = 100.dp,
                        isSelected = isFemaleSelected,
                        onClick = {
                            isFemaleSelected = !isFemaleSelected
                            isMaleSelected = false
                            newGender = "female"
                        }
                    )
                    Text(
                        text = "Female",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(200.dp))
            Row(
                modifier = Modifier.
                fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    modifier = Modifier.width(102.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = GreenSavoro
                    ),
                    shape = RoundedCornerShape(24.dp),
                    onClick = {
                        navController.navigate("Kal3/$newAge/$newGender")
                    },
                    enabled = newGender.isNotEmpty()
                ){
                    Text(text = "Next", style = MaterialTheme.typography.h2.copy(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun MaleSelectedButton(
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
            painter = getMaleImage(isSelected),
            contentDescription = "Male Button",
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
private fun getMaleImage(isSelected: Boolean): Painter {
    return if (isSelected) {
        painterResource(R.drawable.btnmale_active)
    } else {
        painterResource(R.drawable.btnmale_deactive)
    }
}

@Composable
fun FemaleSelectedButton(
    modifier: Modifier = Modifier,
    imageSize: Dp,
    isSelected: Boolean,
    onClick: () -> Unit,
){
    Box(
        modifier = modifier
            .clickable {
                onClick()},
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = getFemaleImage(isSelected),
            contentDescription = "Female Button",
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
private fun getFemaleImage(isSelected: Boolean): Painter {
    return if (isSelected) {
        painterResource(R.drawable.btnfemale_active)
    } else {
        painterResource(R.drawable.btnfemale_deactive)
    }
}