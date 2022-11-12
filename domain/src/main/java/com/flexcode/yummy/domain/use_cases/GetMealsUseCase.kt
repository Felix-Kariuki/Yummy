package com.flexcode.yummy.domain.use_cases

import com.flexcode.yummy.common.utils.Resource
import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetMealsUseCase constructor(
    private val repository: MealsRepository,
) {
    operator fun invoke(
        meal: String,
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<Meals>>> {
        return repository.getMeals(meal, false)
    }
}
