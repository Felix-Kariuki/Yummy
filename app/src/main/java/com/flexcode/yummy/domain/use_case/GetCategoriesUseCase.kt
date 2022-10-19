package com.flexcode.yummy.domain.use_case

import com.flexcode.yummy.domain.models.Categories
import com.flexcode.yummy.domain.repository.CategoriesRepository
import com.flexcode.yummy.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@ViewModelScoped
class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository,
) {

    suspend operator fun invoke(): Flow<Resource<List<Categories>>> {
        return repository.getCategories()
    }
}
