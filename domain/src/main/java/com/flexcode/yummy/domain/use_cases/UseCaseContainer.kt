package com.flexcode.yummy.domain.use_cases

data class UseCaseContainer(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getMealsUseCase: GetMealsUseCase,
    val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
)
