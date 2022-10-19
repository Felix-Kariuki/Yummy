package com.flexcode.yummy.presentation.meals_screen

import com.flexcode.yummy.domain.models.Categories

data class CategoriesState(
    val categories: List<Categories> = emptyList(),
    val isLoading: Boolean = false,
)
