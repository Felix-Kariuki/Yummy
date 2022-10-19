package com.flexcode.yummy.presentation.meals_screen

import com.flexcode.yummy.domain.models.Meals

data class MealsState(
    val meals: List<Meals> = emptyList(),
    val isLoading: Boolean = false,
    val searchMeal: String = "",
    val isRefreshing: Boolean = false
)
