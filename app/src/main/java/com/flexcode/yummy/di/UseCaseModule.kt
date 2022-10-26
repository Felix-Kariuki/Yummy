package com.flexcode.yummy.di

import com.flexcode.yummy.domain.repository.CategoriesRepository
import com.flexcode.yummy.domain.repository.MealsRepository
import com.flexcode.yummy.domain.use_case.GetCategoriesUseCase
import com.flexcode.yummy.domain.use_case.GetMealsByCategoryUseCase
import com.flexcode.yummy.domain.use_case.GetMealsUseCase
import com.flexcode.yummy.domain.use_case.UseCaseContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCaseContainer(
        mealsRepository: MealsRepository,
        categoryRepository: CategoriesRepository
    ): UseCaseContainer {

        return UseCaseContainer(
            getCategoriesUseCase = GetCategoriesUseCase(repository = categoryRepository),
            getMealsUseCase = GetMealsUseCase(repository = mealsRepository),
            getMealsByCategoryUseCase = GetMealsByCategoryUseCase(repository = mealsRepository)
        )
    }
}
