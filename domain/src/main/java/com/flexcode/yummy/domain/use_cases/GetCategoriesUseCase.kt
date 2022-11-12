package com.flexcode.yummy.domain.use_cases

import com.flexcode.yummy.common.utils.Resource
import com.flexcode.yummy.domain.models.Categories
import com.flexcode.yummy.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val repository: CategoriesRepository,
) {

    operator fun invoke(): Flow<Resource<List<Categories>>> {
        return repository.getCategories()
    }
}
