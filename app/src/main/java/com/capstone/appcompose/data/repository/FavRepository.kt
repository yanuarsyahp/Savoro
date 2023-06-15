package com.capstone.appcompose.data.repository

import com.capstone.appcompose.model.Response.FoodResponse
import kotlinx.coroutines.flow.Flow

typealias Foods = List<FoodResponse>

interface FavRepository {
    fun getAllFoodsFromRoom(): Flow<Foods>

    suspend fun getFoodFromRoom(food_id: String): FoodResponse

    suspend fun addFoodToRoom(foodResponse: FoodResponse)

    suspend fun deleteFoodFromRoom(foodResponse: FoodResponse)
}