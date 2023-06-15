package com.capstone.appcompose.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.ui.screen.auth.login.LoginScreen
import com.capstone.appcompose.ui.screen.auth.register.RegisterSrceen
import com.capstone.appcompose.ui.screen.auth.welcome.WelcomeScreen
import com.capstone.appcompose.ui.screen.detail.DetailScreen
import com.capstone.appcompose.ui.screen.error.ErrorHandleScreen
import com.capstone.appcompose.ui.screen.error.UnlockScreen
import com.capstone.appcompose.ui.screen.favourite.FavScreen
import com.capstone.appcompose.ui.screen.foodlist.FoodScreen
import com.capstone.appcompose.viewmodel.FoodViewModel
import com.capstone.appcompose.ui.screen.home.BottomBar
import com.capstone.appcompose.ui.screen.home.HomeScreen
import com.capstone.appcompose.ui.screen.calorie.Cal_1Age
import com.capstone.appcompose.ui.screen.calorie.Cal_2Gender
import com.capstone.appcompose.ui.screen.calorie.Cal_3Height
import com.capstone.appcompose.ui.screen.calorie.Cal_4Weight
import com.capstone.appcompose.ui.screen.calorie.Cal_5Excercise
import com.capstone.appcompose.ui.screen.calorie.Cal_6Result
import com.capstone.appcompose.ui.screen.error.UpcomingScreen
import com.capstone.appcompose.ui.screen.knowledge.KnowledgeScreen
import com.capstone.appcompose.ui.screen.profile.ProfileScreen
import com.capstone.appcompose.ui.screen.recommend.RecommendScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    foodViewModel: FoodViewModel,
    startDestination: String
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Fav.route || currentRoute == Screen.Profile.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ){
            composable(route = Screen.Login.route){
                LoginScreen(navController = navController)
            }
            composable(route = Screen.Register.route){
                RegisterSrceen(navController = navController)
            }
            composable(route = Screen.Welcome.route){
                WelcomeScreen(navController = navController)
            }
            composable(route = Screen.Home.route){
                HomeScreen(navController = navController)
            }
            composable(route = Screen.Fav.route){
                FavScreen(navController = navController)
//                val db = navController.currentBackStackEntry?.arguments?.getParcelable<Food>("food")
//                db?.let {
//                    FavScreen(navController = navController, foods = db)
//                }
            }
            composable(route = Screen.Knowledge.route){
                KnowledgeScreen(navController = navController)
            }
            composable(route = Screen.RecFood.route){
                RecommendScreen(navController, foodViewModel)
            }
            composable(route = Screen.FoodList.route){
                FoodScreen(navController, foodViewModel)
            }
            composable(route = Screen.Detail.route){
                val foodResponse = navController.previousBackStackEntry?.savedStateHandle?.get<FoodResponse>("food")
                foodResponse?.let {
                    DetailScreen(navController = navController, foodResponse = foodResponse)
                }
            }
            composable(
                route = Screen.Profile.route,
                arguments = listOf(
                    navArgument("age"){
                        type = NavType.StringType
                        defaultValue = "-"
                    },
                    navArgument("maintainWeight"){
                        type = NavType.StringType
                        defaultValue = "-"
                    },
                    navArgument("weightLoss"){
                        type = NavType.StringType
                        defaultValue = "-"
                    },
                    navArgument("weightGain"){
                        type = NavType.StringType
                        defaultValue = "-"
                    },
                )
            ){
                ProfileScreen(navController, foodViewModel)
            }
            composable(route = Screen.Kal_1Age.route){
                Cal_1Age(navController = navController)
            }
            composable(
                route = "kal2/{age}",
                arguments = listOf(
                    navArgument("age"){
                        type = NavType.StringType
                    })
            ){
//                Log.d("Args", it.arguments?.getString("age").toString())
                Cal_2Gender(
                    navController = navController,
                    age = it.arguments?.getString("age").toString())
            }
            composable(
                route = "kal3/{age}/{gender}",
                arguments = listOf(
                    navArgument("age"){
                        type = NavType.StringType
                    },
                    navArgument("gender"){
                        type = NavType.StringType
                    }
                )
            ){
                Cal_3Height(
                    navController = navController,
                    age = it.arguments?.getString("age").toString(),
                    gender = it.arguments?.getString("gender").toString())
            }
            composable(
                route = "kal4/{age}/{gender}/{height}",
                arguments = listOf(
                    navArgument("age"){
                        type = NavType.StringType
                    },
                    navArgument("gender"){
                        type = NavType.StringType
                    },
                    navArgument("height"){
                        type = NavType.StringType
                    }
                )
            ){
                Cal_4Weight(
                    navController = navController,
                    age = it.arguments?.getString("age").toString(),
                    gender = it.arguments?.getString("gender").toString(),
                    height = it.arguments?.getString("height"))
            }
            composable(
                route = "kal5/{age}/{gender}/{height}/{weight}",
                arguments = listOf(
                    navArgument("age"){
                        type = NavType.StringType
                    },
                    navArgument("gender"){
                        type = NavType.StringType
                    },
                    navArgument("height"){
                        type = NavType.StringType
                    },
                    navArgument("weight"){
                        type = NavType.StringType
                    }
                )
            ){
                Cal_5Excercise(
                    navController = navController,
                    age = it.arguments?.getString("age").toString(),
                    gender = it.arguments?.getString("gender").toString(),
                    height = it.arguments?.getString("height").toString(),
                    weight = it.arguments?.getString("weight").toString())
            }
            composable(
                route = "kal6/{age}/{gender}/{height}/{weight}/{exercise}",
                arguments = listOf(
                    navArgument("age"){
                        type = NavType.StringType
                    },
                    navArgument("gender"){
                        type = NavType.StringType
                    },
                    navArgument("height"){
                        type = NavType.StringType
                    },
                    navArgument("weight"){
                        type = NavType.StringType
                    },
                    navArgument("exercise"){
                        type = NavType.StringType
                    }
                )
            ){
                Cal_6Result(
                    navController = navController,
                    age = it.arguments?.getString("age").toString(),
                    gender = it.arguments?.getString("gender").toString(),
                    height = it.arguments?.getString("height").toString(),
                    weight = it.arguments?.getString("weight").toString(),
                    exercise = it.arguments?.getString("exercise").toString())
            }
            composable(route = Screen.Error.route){
                ErrorHandleScreen(navController = navController)
            }
            composable(route = Screen.Unlock.route){
                UnlockScreen(navController = navController)
            }
            composable(route = Screen.Upcoming.route){
                UpcomingScreen(navController = navController)
            }
        }
    }
}