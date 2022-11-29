package com.flexcode.yummy.domain.repository

import com.flexcode.yummy.common.utils.Resource
import com.flexcode.yummy.domain.models.Categories
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getCategories(): Flow<Resource<List<Categories>>>
}
