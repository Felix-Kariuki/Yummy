package com.flexcode.yummy.data.repository

import com.flexcode.yummy.common.utils.Resource
import com.flexcode.yummy.data.local.dao.CategoriesDao
import com.flexcode.yummy.data.remote.ApiService
import com.flexcode.yummy.data.remote.mapper.toCategories
import com.flexcode.yummy.data.remote.mapper.toCategoriesEntity
import com.flexcode.yummy.domain.models.Categories
import com.flexcode.yummy.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: CategoriesDao,
) : CategoriesRepository {

    override fun getCategories(): Flow<Resource<List<Categories>>> = flow {
        emit(Resource.Loading())

        val localCategories = dao.getCategories()

        emit(Resource.Loading(data = localCategories.map { it.toCategories() }))

        try {
            val response = apiService.getCategories()
            dao.deleteCategories()
            dao.insertCategories(response.categories.map { it.toCategoriesEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong ${e.message}",
                    data = localCategories.map { it.toCategories() }
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "ERROR!!, check your internet connection!",
                    data = localCategories.map { it.toCategories() }
                )
            )
        }

        val newCategories = dao.getCategories()
            .map { it.toCategories() }
        emit(Resource.Success(newCategories))
    }
}
