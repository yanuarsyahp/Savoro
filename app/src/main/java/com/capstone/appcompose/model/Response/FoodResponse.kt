package com.capstone.appcompose.model.Response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_items")
data class FoodResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val calories: String,
    val carbs: String,
    val fat: String,
    val food_id: String,
    val food_name: String,
    val protein: String
): Parcelable