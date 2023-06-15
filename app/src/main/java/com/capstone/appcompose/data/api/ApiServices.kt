package com.capstone.appcompose.data.api

import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.model.Response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("food-search-menu")
    suspend fun getFood():List<FoodResponse>

    @GET("food-search")
    suspend fun getSearchFood(
        @Query("query") query: String
    ): List<FoodResponse>

    @GET("recommend/{query}")
    suspend fun getRecommendation(
        @Path("query") query: String
    ): List<FoodResponse>

    @GET("documents/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): UserResponse
}