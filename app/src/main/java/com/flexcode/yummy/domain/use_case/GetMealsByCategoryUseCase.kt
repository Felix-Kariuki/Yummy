package com.flexcode.yummy.domain.use_case

import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.domain.repository.MealsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetMealsByCategoryUseCase @Inject constructor(private val repository: MealsRepository) {

    operator fun invoke(category: String): Flow<List<Meals>> {
        return repository.getMealsByCategory(category = category)
    }

}