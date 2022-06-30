package com.flexcode.yummy.presentation

import com.flexcode.yummy.domain.models.Categories
import com.flexcode.yummy.domain.models.Meals

data class MealsState(
    val meals:List<Meals> = emptyList(),
    val isLoading: Boolean = false,
    val searchMeal: String = "",
    val isRefreshing : Boolean = false
)