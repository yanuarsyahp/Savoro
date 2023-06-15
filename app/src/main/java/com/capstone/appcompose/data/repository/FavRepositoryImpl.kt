package com.capstone.appcompose.data.repository

import com.capstone.appcompose.data.database.FavDao
import com.capstone.appcompose.model.Response.FoodResponse

class FavRepositoryImpl(
    private val favDao: FavDao
) : FavRepository {
    override fun getAllFoodsFromRoom() = favDao.getAllItems()

    override suspend fun getFoodFromRoom(food_id: String) = favDao.getItem(food_id)

    override suspend fun addFoodToRoom(foodResponse: FoodResponse) = favDao.addItem(foodResponse)

    override suspend fun deleteFoodFromRoom(foodResponse: FoodResponse) = favDao.deleteItem(foodResponse)
}