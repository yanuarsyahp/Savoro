package com.capstone.appcompose.data.repository

import com.capstone.appcompose.data.api.RetrofitInstance
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.model.Response.UserResponse

class Repository {
    private val repository = RetrofitInstance.apiService

    suspend fun getFoods(): List<FoodResponse>{
        return repository.getFood()
    }

    suspend fun getSearchFoods(query: String): List<FoodResponse>{
        return repository.getSearchFood(query).filter {
            it.food_name.contains(query, ignoreCase = true)
        }
    }

    suspend fun getRecommendation(query: String): List<FoodResponse>{
        return repository.getRecommendation(query).filter {
            it.food_name.contains(query, ignoreCase = true)
        }
    }

    suspend fun getUser(id: String): UserResponse{
        return repository.getUser(id)
    }
}

