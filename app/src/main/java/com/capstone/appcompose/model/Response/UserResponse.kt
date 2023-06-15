package com.capstone.appcompose.model.Response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    val email: String,
    val name: String
): Parcelable