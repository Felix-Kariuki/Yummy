package com.flexcode.yummy.data.di

import android.content.Context
import androidx.room.Room
import com.flexcode.yummy.data.local.dao.CategoriesDao
import com.flexcode.yummy.data.local.dao.MealsDao
import com.flexcode.yummy.data.local.db.MealsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun providesMealsDatabase(@ApplicationContext context: Context): MealsDatabase =
        Room.databaseBuilder(
            context,
            MealsDatabase::class.java,
            MealsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMealsDao(database: MealsDatabase): MealsDao =
        database.mealsDao

    @Provides
    @Singleton
    fun provideCategoriesDao(database: MealsDatabase): CategoriesDao =
        database.categoriesDao
}
