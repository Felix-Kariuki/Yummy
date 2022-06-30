package com.flexcode.yummy.domain.repository

import com.flexcode.yummy.domain.models.Categories
import com.flexcode.yummy.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Flow<Resource<List<Categories>>>
}