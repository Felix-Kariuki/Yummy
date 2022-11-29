package com.flexcode.yummy.data.repository

import com.flexcode.yummy.common.utils.Resource
import com.flexcode.yummy.data.local.dao.MealsDao
import com.flexcode.yummy.data.remote.ApiService
import com.flexcode.yummy.data.remote.mapper.toMeals
import com.flexcode.yummy.data.remote.mapper.toMealsEntity
import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val dao: MealsDao,
    private val apiService: ApiService,
) : MealsRepository {

    override fun getMeals(
        meal: String,
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<Meals>>> {

        return flow {
            emit(Resource.Loading())

            // local
            val localMeals = dao.getMeals(meal)
                .map { it.toMeals() }
            emit(Resource.Success(data = localMeals))

            val noLocalCache = localMeals.isEmpty()
            val loadFromCache = !noLocalCache && !fetchFromRemote

            if (loadFromCache) {
                emit(Resource.Loading())
                return@flow
            }

            try {
                val remoteMeals = apiService.getMeals(meal)
                remoteMeals.meals?.let { mealsDto ->
                    emit(Resource.Success(mealsDto.map { it.toMeals() }))
                    mealsDto.map { it.strSource }
                        .let { dao.deleteMeals(it as List<String>) }
                    dao.addMeals(mealsDto.map { it.toMealsEntity() })
                }
            } catch (e: HttpException) {
                Resource.Error(
                    message = "${e.message}",
                    data = localMeals
                )
            } catch (e: IOException) {
                Resource.Error(
                    message = "${e.message}",
                    data = localMeals
                )
            }

            val newMeals = dao.getMeals(meal)
                .map { it.toMeals() }
            emit(Resource.Success(newMeals))
        }
    }

    override fun getMealsByCategory(category: String): Flow<List<Meals>> {
        return dao.getMealsByCategory(category = category)
            .map {

                meals ->
                meals.map { it.toMeals() }
            }
    }
}
