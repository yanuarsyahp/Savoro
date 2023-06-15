package com.capstone.appcompose.ui.screen.auth.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.navigation.Screen
import com.capstone.appcompose.ui.theme.GreenSavoro
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavController,
){
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreRepository(context)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        HorizontalPager(
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        Spacer(modifier = Modifier.height(70.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = GreenSavoro,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(36.dp))
        FinishButton(modifier = Modifier.padding(bottom = 15.dp), pagerState = pagerState) {
            scope.launch {
                dataStore.saveOnBoarding(Screen.Login.route)
            }
            navController.navigate(Screen.Login.route){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
        )
        Text(
           text = onBoardingPage.title,
           fontWeight = FontWeight.Bold,
           textAlign = TextAlign.Center,
           style = MaterialTheme.typography.h4.copy(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        )
        Text(
            text = onBoardingPage.description,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 10.dp),
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
){
    Row(
        modifier = modifier.padding(horizontal = 24.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ){
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = GreenSavoro,
                    contentColor = Color.White),
                modifier = Modifier.height(42.dp),
                shape = RoundedCornerShape(9.dp)
            ){
                Text(text = "Finish",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4.copy(fontSize = 14.sp))
            }
        }
    }
}