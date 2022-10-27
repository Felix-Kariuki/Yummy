package com.flexcode.yummy.domain.use_case

data class UseCaseContainer(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getMealsUseCase: GetMealsUseCase,
    val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
)
