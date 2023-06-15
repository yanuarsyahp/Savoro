package com.capstone.appcompose.model.HomeModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: String,
    val desc: String,
    val img: Int,
): Parcelable
