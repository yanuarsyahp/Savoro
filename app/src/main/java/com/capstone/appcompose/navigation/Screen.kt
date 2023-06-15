package com.capstone.appcompose.navigation

sealed class Screen (val route: String){
    object Welcome: Screen(route = "welcome_screen")
    object Login: Screen(route = "login_screen")
    object Register: Screen(route = "register_screen")
    object Home: Screen(route = "home_screen")
    object Fav: Screen(route = "fav_screen")
    object Profile: Screen(route = "profile_screen")
    object Kal_1Age: Screen(route = "kal1")
    object FoodList: Screen(route = "food_list")
    object Detail: Screen(route = "detail_screen")
    object Knowledge: Screen(route = "knowledge_screen")
    object RecFood: Screen(route = "rec_food")
    object Unlock: Screen(route = "unlock_screen")
    object Upcoming: Screen(route = "upcoming_screen")
    object Error: Screen(route = "error_screen")
}
