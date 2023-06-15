package com.capstone.appcompose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.appcompose.navigation.Screen
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")
class DataStoreRepository(context: Context) {

    private object PreferencesKey{
        val onBoardingKey = stringPreferencesKey("on_boarding")
        val USER_TOKEN = stringPreferencesKey("user_token")
        val MAINTAIN_WEIGHT_KEY = stringPreferencesKey("maintain_weight")
        val WEIGHT_LOSS_KEY = stringPreferencesKey("weight_loss")
        val WEIGHT_GAIN_KEY = stringPreferencesKey("weight_gain")
        val AGE_KEY = stringPreferencesKey("age")
        val REC_FOOD = stringPreferencesKey("rec_food")
    }

    private val dataStore = context.dataStore

    val getRecFood: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.REC_FOOD] ?: ""
        }
    suspend fun saveRecFood(recFood: String){
        dataStore.edit { preference ->
            preference[PreferencesKey.REC_FOOD] = recFood
        }
    }

    val getOnBoaring: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.onBoardingKey] ?: Screen.Welcome.route
        }
    suspend fun saveOnBoarding(onBoarding: String){
        dataStore.edit { preference ->
            preference[PreferencesKey.onBoardingKey] = onBoarding
        }
    }

    val getUserToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.USER_TOKEN] ?: ""
        }

    suspend fun saveUserToken(userToken: String){
        dataStore.edit { preference ->
            preference[PreferencesKey.USER_TOKEN] = userToken
        }
    }

    val getMaintainWeight: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.MAINTAIN_WEIGHT_KEY] ?: "-"
        }

    suspend fun saveMaintainWeight(maintainWeight: String){
        dataStore.edit { preferences ->
            preferences[PreferencesKey.MAINTAIN_WEIGHT_KEY] = maintainWeight
        }
    }

    val getWeightLoss: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.WEIGHT_LOSS_KEY] ?: "-"
        }

    suspend fun saveWeightLoss(weightLoss: String){
        dataStore.edit { preferences ->
            preferences[PreferencesKey.WEIGHT_LOSS_KEY] = weightLoss
        }
    }

    val getWeightGain: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.WEIGHT_GAIN_KEY] ?: "-"
        }

    suspend fun saveWeightGain(weightGain: String){
        dataStore.edit { preferences ->
            preferences[PreferencesKey.WEIGHT_GAIN_KEY] = weightGain
        }
    }

    val getAge: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.AGE_KEY] ?: "-"
        }

    suspend fun saveAge(weightGain: String){
        dataStore.edit { preferences ->
            preferences[PreferencesKey.AGE_KEY] = weightGain
        }
    }
}