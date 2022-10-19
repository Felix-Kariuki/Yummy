package com.flexcode.yummy.domain.repository

import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    suspend fun getMeals(meal: String, fetchFromRemote: Boolean): Flow<Resource<List<Meals>>>
}
