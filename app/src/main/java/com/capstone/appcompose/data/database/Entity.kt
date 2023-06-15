package com.capstone.appcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capstone.appcompose.model.Response.FoodResponse

@Database(
    entities = [FoodResponse::class],
    version = 1,
    exportSchema = false
)
abstract class Entity : RoomDatabase() {
    abstract val favDao: FavDao
}