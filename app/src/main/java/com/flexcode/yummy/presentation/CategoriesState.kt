package com.flexcode.yummy.presentation

import com.flexcode.yummy.domain.models.Categories

data class CategoriesState(
    val categories: List<Categories> = emptyList(),
    val isLoading: Boolean = false,
)