@file:OptIn(ExperimentalMaterial3Api::class)

package com.capstone.appcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.appcompose.ui.theme.GreenSavoro

@Composable
fun InputField(
    labelValue: String,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
){
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        label = { Text(text = labelValue) },
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = GreenSavoro,
            focusedBorderColor = GreenSavoro,
            cursorColor = GreenSavoro,
        ),
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    labelValue: String,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
){
    val localFocusManager = LocalFocusManager.current
    val password = remember {mutableStateOf("")}

    val passwordVisible = remember {mutableStateOf(false)}

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = GreenSavoro,
            focusedBorderColor = GreenSavoro,
            cursorColor = GreenSavoro,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            val description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Image(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun MyButton(
    value: String,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp),
        onClick ={
            onButtonClicked.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = GreenSavoro
        ),
        shape = RoundedCornerShape(9.dp),
        enabled = isEnabled
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
        ){
            Text(
                text = value,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ){
        Icon(
           imageVector = Icons.Filled.KeyboardArrowUp,
           contentDescription = null,
            tint = Color.White
        )
    }
}