package com.flexcode.yummy.domain.repository

import com.flexcode.yummy.domain.models.Categories
import kotlinx.coroutines.flow.Flow
import com.flexcode.yummy.core.utils.Resource


interface CategoriesRepository {
    fun getCategories(): Flow<Resource<List<Categories>>>
}
