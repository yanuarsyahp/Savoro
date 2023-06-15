package com.capstone.appcompose.ui.screen.auth.login

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.capstone.appcompose.component.InputField
import com.capstone.appcompose.component.MyButton
import com.capstone.appcompose.component.PasswordInput
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.navigation.Screen
import com.capstone.appcompose.ui.theme.GreenSavoro
import com.capstone.appcompose.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreRepository(context)

    if(loginViewModel.loginInProgress.value) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                color = GreenSavoro,
                modifier = Modifier
                    .align(alignment = Alignment.Center),
            )
        }
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Sign up",
            color = GreenSavoro,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4.copy(fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.End)
                .clickable {
                    navController.navigate(Screen.Register.route)
                }
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Sign in",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Here\'s your first step\nwith us!",
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp),
        )
        Spacer(modifier = Modifier.height(56.dp))
        InputField(
            labelValue = "Email",
            onTextChanged = {
                loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
            },
            errorStatus = loginViewModel.loginUiState.value.emailError
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordInput(
            labelValue = "Password",
            onTextSelected = {
                loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
            },
            errorStatus = loginViewModel.loginUiState.value.passwordError
        )
        Spacer(modifier = Modifier.height(250.dp))
        OutlinedButton(
            onClick = {
                scope.launch {
                    dataStore.saveOnBoarding(Screen.Home.route)
                }
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            },
            border = BorderStroke(2.dp, color = GreenSavoro),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        ) {
            Text(
                text = "Start now",
                color = GreenSavoro,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4.copy(fontSize = 14.sp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        MyButton(
            value = "Log in",
            onButtonClicked = {

                loginViewModel.loginInProgress.value = true
                val email = loginViewModel.loginUiState.value.email
                val password = loginViewModel.loginUiState.value.password
                val auth = FirebaseAuth.getInstance()

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                        if(it.isSuccessful){
                            val token = auth.currentUser?.uid
                            loginViewModel.loginInProgress.value = false
                            scope.launch {
                                if (token != null) {
                                    dataStore.saveUserToken(token)
                                }
                                dataStore.saveOnBoarding(Screen.Home.route)
                            }
                            navController.navigate(Screen.Home.route){
                                popUpTo(navController.graph.id){
                                    inclusive = true
                                }
                            }
                        }
                    }
                    .addOnFailureListener {
                        loginViewModel.loginInProgress.value = false
                    }
            },
            isEnabled = loginViewModel.allValidationsPassed.value
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Don't have an account?",
                fontSize = 10.sp,
                color = Color.Gray
            )
            Text(
                text = " Sign up",
                fontSize = 10.sp,
                color = GreenSavoro,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.Register.route)
                    }
            )
        }
    }
}
