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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.ui.theme.GreenSavoro

@Composable
fun Cal_4Weight(
    navController: NavController,
    age: String?,
    gender: String?,
    height: String?
){
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var weight by remember { mutableStateOf("") }

    var newAge = age
    var newGender = gender
    var newHeight = height

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
                .weight(1f)
        ){
            Image(
                painter = painterResource(id = R.drawable.icon_kcal_outlined),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
            Spacer(modifier = Modifier.height(150.dp))
            Text(
                text = "Input your weight",
                style = MaterialTheme.typography.h2.copy(fontSize = 24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            TextField(
                value = weight,
                onValueChange = { newWeight ->
                    weight = newWeight
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(textAlign = TextAlign.Center, color = Color.White, fontSize = 95.sp),
                modifier = Modifier.focusRequester(focusRequester),
                maxLines = 1
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
                focusManager.moveFocus(FocusDirection.Up)
            }
            Text(
                text = "kg",
                style = MaterialTheme.typography.h2.copy(fontSize = 24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
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
                enabled = weight.isNotEmpty(),
                shape = RoundedCornerShape(24.dp),
                onClick = { navController.navigate("Kal5/$newAge/$newGender/$newHeight/$weight") }
            ){
                Text(text = "Next", style = MaterialTheme.typography.h2.copy(fontSize = 20.sp), fontWeight = FontWeight.Bold)
            }
        }
    }
}