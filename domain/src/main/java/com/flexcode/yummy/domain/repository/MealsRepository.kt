package com.flexcode.yummy.domain.repository

import com.flexcode.yummy.common.utils.Resource
import com.flexcode.yummy.domain.models.Meals
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    fun getMeals(meal: String, fetchFromRemote: Boolean): Flow<Resource<List<Meals>>>
    fun getMealsByCategory(category: String): Flow<List<Meals>>
}
