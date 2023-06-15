@file:OptIn(ExperimentalComposeUiApi::class)

package com.capstone.appcompose.ui.screen.foodlist

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.capstone.appcompose.R
import com.capstone.appcompose.component.FoodCard
import com.capstone.appcompose.component.ScrollToTopButton
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.model.Response.FoodResponse
import com.capstone.appcompose.ui.theme.GreenSavoro
import com.capstone.appcompose.viewmodel.FoodViewModel
import kotlinx.coroutines.launch

@Composable
fun FoodScreen(
    navController: NavController,
    viewModel: FoodViewModel,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreRepository(context)

    val foodList by viewModel.Foods.observeAsState(emptyList())
    val listState = rememberLazyListState()
    val showButton: Boolean by remember{
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    var query by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit){
        viewModel.getFoods()
    }

    Box(modifier = modifier){
        LazyColumn (
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ){
            item{
                Row(
                    modifier = Modifier
                        .background(GreenSavoro)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                    TextField(
                        value = query,
                        onValueChange = {newQuery ->
                            query = newQuery
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {Text("Search food")},
                        modifier = modifier.clip(RoundedCornerShape(8.dp)),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.getSearchFood(query)
                                scope.launch {
                                    dataStore.saveRecFood(query)
                                    Log.d("rec","kesimpen rec querynya: ")
                                }
                                Log.d("rec","rec query: ${dataStore.getRecFood}")
                            },
                        )
                    )
                    Icon(
                        painter = painterResource(R.drawable.filter),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            items(foodList){food: FoodResponse ->
                FoodCard(
                    food, navController
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(onClick = {
                scope.launch {
                    listState.scrollToItem(index = 0)
                }
            })
        }
    }
}