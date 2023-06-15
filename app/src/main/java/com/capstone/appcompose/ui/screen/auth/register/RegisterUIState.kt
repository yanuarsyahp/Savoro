package com.capstone.appcompose.ui.screen.auth.register

data class RegisterUIState(
    var Name :String = "",
    var email  :String = "",
    var password  :String = "",


    var NameError :Boolean = false,
    var emailError :Boolean = false,
    var passwordError : Boolean = false,
)