package com.capstone.appcompose.ui.screen.auth.register

sealed class RegisterUIEvent {
    data class NameChanged(val Name:String) : RegisterUIEvent()
    data class EmailChanged(val email:String): RegisterUIEvent()
    data class PasswordChanged(val password: String) : RegisterUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : RegisterUIEvent()

    object RegisterButtonClicked : RegisterUIEvent()
}