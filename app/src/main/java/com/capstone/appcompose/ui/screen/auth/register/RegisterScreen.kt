package com.capstone.appcompose.ui.screen.auth.register

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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.component.InputField
import com.capstone.appcompose.component.MyButton
import com.capstone.appcompose.component.PasswordInput
import com.capstone.appcompose.ui.theme.GreenSavoro
import com.capstone.appcompose.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegisterSrceen(
    navController: NavController,
    registerViewModel: RegisterViewModel = viewModel()
){
    if(registerViewModel.signUpInProgress.value) {
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
    ){

        AppBar(navController)
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Sign up",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Enter infornation to create\nyour account",
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp),
        )
        Spacer(modifier = Modifier.height(56.dp))
        InputField(
            labelValue = "Email",
            onTextChanged = {
                registerViewModel.onEvent(RegisterUIEvent.EmailChanged(it))
            },
            errorStatus = registerViewModel.registrationUIState.value.emailError
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            labelValue = "Full name",
            onTextChanged = {
                registerViewModel.onEvent(RegisterUIEvent.NameChanged(it))
            },
            errorStatus = registerViewModel.registrationUIState.value.NameError
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordInput(
            labelValue = "Password",
            onTextSelected = {
                registerViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
            },
            errorStatus = registerViewModel.registrationUIState.value.passwordError
        )
        Spacer(modifier = Modifier.height(233.dp))
        MyButton(
            value = "Register",
            onButtonClicked = {

                registerViewModel.signUpInProgress.value = true
                val firestore = FirebaseFirestore.getInstance()
                val auth = FirebaseAuth.getInstance()
                val email = registerViewModel.registrationUIState.value.email
                val name = registerViewModel.registrationUIState.value.Name
                val password = registerViewModel.registrationUIState.value.password

                FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                        registerViewModel.signUpInProgress.value = false
                        if (it.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            if (userId != null){
                                val userMap: Map<String, Any> = hashMapOf(
                                    "name" to name,
                                    "email" to email,
                                )
                                val userDocRef = firestore.collection("users").document(userId)
                                userDocRef.set(userMap)
                                    .addOnSuccessListener {
                                        navController.popBackStack()
                                    }
                                    .addOnFailureListener {
                                        // Failed to register
                                        // Handle the error
                                    }
                            }
                        }

                    }
                    .addOnFailureListener {
//                        Log.d(TAG, "Inside_OnFailureListener")
//                        Log.d(TAG, "Exception= ${it.message}")
//                        Log.d(TAG, "Exception= ${it.localizedMessage}")
                    }
            },
            isEnabled = registerViewModel.allValidationsPassed.value
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 10.sp,
                color = Color.Gray
            )
            Text(
                text = " Sign in",
                fontSize = 10.sp,
                color = GreenSavoro,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }

    }
}

@Composable
private fun AppBar(navController: NavController){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
            }
        )
        Text(
           text = "Sign in",
           color = GreenSavoro,
           fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4.copy(fontSize = 16.sp),
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}