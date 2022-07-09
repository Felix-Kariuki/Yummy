package com.flexcode.yummy.di

import com.flexcode.yummy.domain.repository.CategoriesRepository
import com.flexcode.yummy.domain.repository.MealsRepository
import com.flexcode.yummy.domain.use_case.GetCategoriesUseCase
import com.flexcode.yummy.domain.use_case.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGetMealsUseCase(repository: MealsRepository): GetMealsUseCase {
        return GetMealsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetCategoriesUseCase(repository: CategoriesRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }

}