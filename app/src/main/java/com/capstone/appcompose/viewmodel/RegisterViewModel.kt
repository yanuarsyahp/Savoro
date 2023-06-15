package com.capstone.appcompose.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.capstone.appcompose.Validator.Validator
import com.capstone.appcompose.ui.screen.auth.register.RegisterUIEvent
import com.capstone.appcompose.ui.screen.auth.register.RegisterUIState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {

    private val TAG = RegisterViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegisterUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.NameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    Name = event.Name
                )
                printState()
            }

            is RegisterUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }

            is RegisterUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is RegisterUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is RegisterUIEvent.PrivacyPolicyCheckBoxClicked -> TODO()
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            name = registrationUIState.value.Name,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val NameResult = Validator.validateFirstName(
            fName = registrationUIState.value.Name
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "NameResult= $NameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")

        registrationUIState.value = registrationUIState.value.copy(
            NameError = NameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )


        allValidationsPassed.value = NameResult.status  &&
                emailResult.status && passwordResult.status

    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }

    private fun createUserInFirebase(email: String, name: String, password: String) {

        signUpInProgress.value = true

        val firestore = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false
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
                            }
                            .addOnFailureListener {
                                // Failed to register
                                // Handle the error
                            }
                    }
                }

            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
    }

}