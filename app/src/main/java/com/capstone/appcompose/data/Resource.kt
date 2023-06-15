package com.capstone.appcompose.data

import java.lang.Exception

sealed class Result <T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: String, data: T? = null): Result<T>(data,message)
    class Loading<T>(data: T? = null) : Result<T>(data)
}

//sealed class Result<T>(val data: T? = null, val message: String? = null) {
//    class Success<T>(data: T?) : Result<T>(data = data)
//    class Error<T>(message: String?) : Result<T>(message = message)
//}

sealed class Resource<out T>{
    data class Success<out T>(val result: T): Resource<T>()
    data class Failure(val exception: Exception): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}