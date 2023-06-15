package com.capstone.appcompose.ui.screen.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.ui.theme.RedSavoro

@Composable
fun ErrorHandleScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.errorhandle),
            contentDescription = "error404",
            modifier = Modifier.size(220.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick ={
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RedSavoro
            ),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier
                .width(115.dp)
        ) {
            Text(
                text = "Back",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4.copy(fontSize = 14.sp)
            )
        }
    }
}