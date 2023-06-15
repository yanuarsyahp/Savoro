package com.capstone.appcompose.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.model.Response.UserResponse
import com.capstone.appcompose.navigation.Screen
import com.capstone.appcompose.ui.theme.GreenSavoro
import com.capstone.appcompose.viewmodel.FoodViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: FoodViewModel,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreRepository(context)

    val maintainWeight = dataStore.getMaintainWeight.collectAsState(initial = "-")
    val weightLoss = dataStore.getWeightLoss.collectAsState(initial = "-")
    val weightGain = dataStore.getWeightGain.collectAsState(initial = "-")
    val newAge = dataStore.getAge.collectAsState(initial = "-")

    val newToken = dataStore.getUserToken.collectAsState(initial = "")
    var showDialog by remember{ mutableStateOf(false) }
    var username by remember{ mutableStateOf("Guest") }

    val responseData by viewModel.responseData.observeAsState()

    if(newToken.value.isNotEmpty()){
        LaunchedEffect(Unit){
            viewModel.getUser(newToken.value)
        }
    }



    Column(){
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.h2.copy(fontSize = 32.sp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (newToken.value.isNotEmpty()){
                    "Log out"
                }else{
                    "Sign in"
                },
                color = GreenSavoro,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4.copy(fontSize = 16.sp),
                modifier = Modifier.clickable {
                    showDialog = true
                }
            )
            if(showDialog){
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Return to login page", fontWeight = FontWeight.Bold) },
                    text = { Text(text = "You will be redirected back to the login page, continue?") },
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
                            scope.launch {
                                dataStore.saveOnBoarding(Screen.Login.route)
                                dataStore.saveUserToken("")
                                username = "Guest"
                            }
                            navController.navigate(Screen.Login.route){
                                popUpTo(navController.graph.id){
                                    inclusive = true
                                }
                            }
                            showDialog = false
                        }) {
                            Text(text = "Confirm", color = GreenSavoro)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.account_circle),
                contentDescription = "account_image",
                modifier = Modifier
                    .size(200.dp)
            )
            if(newToken.value.isNotEmpty()){
                responseData?.let{user: UserResponse ->
                    username = user.name
                }
            }
            Text(
                text = username,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(58.dp))

        Divider(
            color = Color.Black,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        //Maintain weight
        ProfileData(weight = "Maintain weight", value = maintainWeight.value)
        //Weight loss
        ProfileData(weight = "Weight loss", value = weightLoss.value)
        //Weight gain
        ProfileData(weight = "Weight gain", value = weightGain.value)
        //Age
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Age",
                    fontSize = 20.sp
                )
                Text(
                    text = newAge.value,
                    style = MaterialTheme.typography.h4.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 32.dp)
                )
            }
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun ProfileData(weight: String? = null, value: String? = null){
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = weight.toString(),
                fontSize = 20.sp
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.h4.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "calories/day",
                    fontSize = 14.sp
                )
            }
        }
        Divider(
            color = Color.Black,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )
    }
}