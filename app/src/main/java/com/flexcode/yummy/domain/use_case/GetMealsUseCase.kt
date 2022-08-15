package com.flexcode.yummy.domain.use_case


import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.domain.repository.MealsRepository
import com.flexcode.yummy.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetMealsUseCase @Inject constructor(
    private val repository: MealsRepository,
) {
    suspend operator fun invoke(
        meal: String,
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<Meals>>> {
        return repository.getMeals(meal, false)
    }
}
