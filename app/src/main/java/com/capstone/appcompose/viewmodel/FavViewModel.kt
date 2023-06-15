package com.capstone.appcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.appcompose.data.repository.FavRepository
import com.capstone.appcompose.model.Response.FoodResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val repo: FavRepository
): ViewModel() {

    var foodResponse by mutableStateOf(FoodResponse(0,"","","","","",""))
        private set
    var openDialog by mutableStateOf(false)

    val foods = repo.getAllFoodsFromRoom()


    fun addFood(foodResponse: FoodResponse) = viewModelScope.launch {
        repo.addFoodToRoom(foodResponse)
    }

    fun deleteFood(foodResponse: FoodResponse) = viewModelScope.launch {
        repo.deleteFoodFromRoom(foodResponse)
    }

}