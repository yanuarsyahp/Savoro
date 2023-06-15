package com.capstone.appcompose.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.capstone.appcompose.R
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.model.HomeModel.ItemData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val dataStore = DataStoreRepository(context)
    val newToken = dataStore.getUserToken.collectAsState(initial = "")

    val listState = rememberLazyListState()
    Scaffold( 
        bottomBar = { BottomBar(navController) },
        modifier = modifier
    ){
        Column(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        ){
            Text(
                text = stringResource(R.string.savoro),
                style = MaterialTheme.typography.h2.copy(fontSize = 32.sp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(
                state = listState,
                modifier = Modifier.padding(vertical = 18.dp)
            ){
                items(ItemData.Items, key = {it.id}){ item ->
                    ItemList(
                        item = item,
                        navController = navController,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
