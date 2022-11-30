package com.flexcode.yummy.data.di

import com.flexcode.yummy.data.repository.CategoriesRepositoryImpl
import com.flexcode.yummy.data.repository.MealsRepositoryImpl
import com.flexcode.yummy.domain.repository.CategoriesRepository
import com.flexcode.yummy.domain.repository.MealsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindMealsRepository(repository: MealsRepositoryImpl):
        MealsRepository

    @Binds
    @Singleton
    fun bindCategoriesRepository(repository: CategoriesRepositoryImpl):
        CategoriesRepository
}
