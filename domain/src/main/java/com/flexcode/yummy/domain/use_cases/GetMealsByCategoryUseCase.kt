package com.flexcode.yummy.domain.use_cases

import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetMealsByCategoryUseCase(private val repository: MealsRepository) {

    operator fun invoke(category: String): Flow<List<Meals>> {
        return repository.getMealsByCategory(category = category)
    }
}
