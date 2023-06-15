package com.capstone.appcompose.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.data.repository.Repository
import com.capstone.appcompose.model.Response.UserResponse
import kotlinx.coroutines.launch

class FoodViewModel: ViewModel() {

    val TAG = FoodViewModel::class.simpleName
    private val repository = Repository()

    private val _foods = MutableLiveData<List<FoodResponse>>()
    val Foods: LiveData<List<FoodResponse>> = _foods

    private val _recfoods = MutableLiveData<List<FoodResponse>>()
    val RecFoods: LiveData<List<FoodResponse>> = _recfoods

    fun getFoods(){
        viewModelScope.launch {
            try{
                val foods = repository.getFoods()
                _foods.value = foods
            }catch (e:Exception){
                //
            }
        }
    }


    fun getSearchFood(newQuery: String){
        viewModelScope.launch {
            try{
                val foods = repository.getSearchFoods(newQuery)
                _foods.value = foods
                Log.d(TAG, "masuk search $newQuery = ${_foods.value}")
            }catch (e:Exception){
                Log.d(TAG,"error gan = $e")
            }
        }
    }

    fun getRecommendation(newQuery: String){
        viewModelScope.launch {
            try{
                val foods = repository.getRecommendation(newQuery)
                _recfoods.value = foods
                Log.d(TAG, "masuk recommendation $newQuery = ${_recfoods.value}")
            }catch (e:Exception){
                Log.d(TAG,"error gan = $e")
            }
        }
    }

    private val _responseData = MutableLiveData<UserResponse>()
    val responseData: LiveData<UserResponse> = _responseData
    fun getUser(id: String){
        viewModelScope.launch {
            try{
                val user = repository.getUser(id)
                _responseData.value = user
                Log.d(TAG, "fungsi berhasil: ${_responseData.value}")
            }catch (e:Exception){
                Log.d(TAG, "fungsi gagal: $e")
            }
        }
    }

}