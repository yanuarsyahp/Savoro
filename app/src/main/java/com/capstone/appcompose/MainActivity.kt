package com.capstone.appcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.navigation.Screen
import com.capstone.appcompose.navigation.SetupNavGraph
import com.capstone.appcompose.viewmodel.FoodViewModel
import com.capstone.appcompose.ui.theme.AppComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: FoodViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppComposeTheme {
                val context = LocalContext.current
                val dataStore = DataStoreRepository(context)
                val screen = dataStore.getOnBoaring.collectAsState(initial = Screen.Welcome.route)
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    startDestination = screen.value.toString(),
                    foodViewModel = viewModel
                )
            }
        }
    }
}