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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.ui.theme.GreenSavoro
import com.capstone.appcompose.ui.theme.GreySavoro

@Composable
fun Cal_5Excercise(
    navController: NavController,
    age: String?,
    gender: String?,
    height: String?,
    weight: String?
) {
    var newAge = age
    var newGender = gender
    var newHeight = height
    var newWeight = weight
    var exercise by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenSavoro)
    )
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        AppBarExercise(navController = navController)
        Spacer(modifier = Modifier.height(22.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_kcal_outlined),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
            Spacer(modifier = Modifier.height(189.dp))
            Text(
                text = "Exercise",
                style = MaterialTheme.typography.h2.copy(fontSize = 24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            ////////////////////////////////////////////////////////
            //DROPDOWN
            var expanded by remember { mutableStateOf(false) }

            val list = listOf(
                "Sedentary",
                "Lightly active",
                "Moderately active",
            )

            var textfieldSize by remember { mutableStateOf(Size.Zero) }

            val icon = if (expanded) {
                Icons.Filled.KeyboardArrowUp
            } else {
                Icons.Filled.KeyboardArrowDown
            }

            Column() {
                OutlinedTextField(
                    value = exercise,
                    onValueChange = { exercise = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = { Text("Choose your activity", color = GreySavoro) },
                    trailingIcon = {
                        Icon(
                            icon, "contentDescription",
                            Modifier
                                .clickable { expanded = !expanded }
                                .size(35.dp),
                            tint = GreenSavoro,
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    readOnly = true,
                    )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    list.forEach { label ->
                        DropdownMenuItem(onClick = {
                            exercise = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }
            //DROPDOWN
            ////////////////////////////////////////////////////////
        }
        Spacer(modifier = Modifier.height(271.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier.width(102.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = GreenSavoro
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = exercise.isNotEmpty(),
                onClick = { navController.navigate("kal6/$newAge/$newGender/$newHeight/$newWeight/$exercise") }
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.h2.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun AppBarExercise(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
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
        Icon(
            painter = painterResource(R.drawable.ic_baseline_info),
            contentDescription = null,
            tint = Color.White
        )
    }
}

