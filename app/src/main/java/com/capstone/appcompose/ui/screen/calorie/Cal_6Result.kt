package com.capstone.appcompose.ui.screen.calorie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.navigation.Screen
import com.capstone.appcompose.ui.theme.GreenSavoro
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun Cal_6Result(
    navController: NavController,
    age: String,
    gender: String,
    height: String,
    weight: String,
    exercise: String
){
    var bmr by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    //Please provide an age between 15 and 80.

    bmr = calculate_bmr(weight, height, age, gender)
    result = daily_calories(bmr,exercise).toString()

    val maintainWeight by remember { mutableStateOf(BigDecimal(result.toDouble()).setScale(0, RoundingMode.DOWN).toInt().toString()) }
    val weightLoss by remember { mutableStateOf(BigDecimal(result.toDouble() * 0.72).setScale(0, RoundingMode.DOWN).toInt().toString()) }
    val weightGain by remember { mutableStateOf(BigDecimal(result.toDouble() * 1.28).setScale(0, RoundingMode.DOWN).toInt().toString()) }

    val cal = "Calories/day"

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreRepository(context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenSavoro)
    )
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .padding(top = 46.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Result",
            style = MaterialTheme.typography.h2.copy(fontSize = 40.sp),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(70.dp))
        ResultContainer(
            suffix = "Maintain weight",
            result = maintainWeight,
            prefix = cal
        )
        Spacer(modifier = Modifier.height(41.dp))
        ResultContainer(
            suffix = "Weight loss",
            result = weightLoss,
            prefix = cal
        )
        Spacer(modifier = Modifier.height(41.dp))
        ResultContainer(
            suffix = "Weight gain",
            result = weightGain,
            prefix = cal
        )
        Spacer(modifier = Modifier.height(70.dp))
        Button(
            modifier = Modifier.width(235.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = GreenSavoro
            ),
            shape = RoundedCornerShape(24.dp),
            onClick = {
                scope.launch {
                    dataStore.saveMaintainWeight(maintainWeight)
                    dataStore.saveWeightLoss(weightLoss)
                    dataStore.saveWeightGain(weightGain)
                    dataStore.saveAge(age)
                }
                navController.navigate(Screen.Profile.route){
                    popUpTo(navController.graph.id){
                        inclusive = true
                    }
                }
            }
        ) {
            Text(
                text = "Finish",
                style = MaterialTheme.typography.h2.copy(fontSize = 20.sp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ResultContainer(suffix: String, result: String, prefix: String){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = suffix,
            style = MaterialTheme.typography.h2.copy(fontSize = 24.sp),
            color = Color.White
        )
        Text(
            text = result,
            style = MaterialTheme.typography.h2.copy(fontSize = 36.sp),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = prefix,
            style = MaterialTheme.typography.h2.copy(fontSize = 24.sp),
            color = Color.White
        )
    }
}

private fun calculate_bmr(weight: String, height: String, age: String, gender: String): String{
    return if (gender == "male"){
        (10 * weight.toDouble()) + (6.25f * height.toDouble()) - (5 * age.toDouble()) + 5
    } else {
        (10 * weight.toDouble()) + (6.25f * height.toDouble()) - (5 * age.toDouble()) - 161
    }.toString()
}

private fun daily_calories(bmr: String, exercise: String): Double {
    return if (exercise == "Sedentary"){
        bmr.toDouble() * 1.2
    }else if (exercise == "Lightly active"){
        bmr.toDouble() * 1.375
    } else {
        bmr.toDouble() * 1.55
    }
}