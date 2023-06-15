package com.capstone.appcompose.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.capstone.appcompose.data.repository.Foods
import com.capstone.appcompose.model.Response.FoodResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDao {
    @Query("SELECT * FROM favorite_items ORDER BY food_id ASC")
    fun getAllItems(): Flow<Foods>

    @Query("SELECT * FROM favorite_items WHERE food_id = :food_id")
    suspend fun getItem(food_id: String): FoodResponse

    @Insert(onConflict = IGNORE)
    suspend fun addItem(foodResponse: FoodResponse)

    @Delete
    suspend fun deleteItem(foodResponse: FoodResponse)
}