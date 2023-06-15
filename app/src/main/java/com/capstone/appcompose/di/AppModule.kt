package com.capstone.appcompose.di

import android.content.Context
import androidx.room.Room
import com.capstone.appcompose.data.database.Entity
import com.capstone.appcompose.data.database.FavDao
import com.capstone.appcompose.data.repository.DataStoreRepository
import com.capstone.appcompose.data.repository.FavRepository
import com.capstone.appcompose.data.repository.FavRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFoodDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        Entity::class.java,
        "favorite_items"
    ).build()

    @Provides
    fun provideFoodDao(
        entity: Entity
    ) = entity.favDao

    @Provides
    fun provideFavRepository(
        favDao: FavDao
    ): FavRepository = FavRepositoryImpl(
        favDao = favDao
    )

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
}