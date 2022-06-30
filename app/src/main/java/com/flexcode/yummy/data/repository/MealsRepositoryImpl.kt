package com.flexcode.yummy.data.repository

import coil.network.HttpException
import com.flexcode.yummy.data.local.MealsDao
import com.flexcode.yummy.data.remote.ApiService
import com.flexcode.yummy.data.remote.mapper.toMeals
import com.flexcode.yummy.data.remote.mapper.toMealsEntity
import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.domain.repository.MealsRepository
import com.flexcode.yummy.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MealsRepositoryImpl(
    private val dao: MealsDao,
    private val apiService: ApiService
) : MealsRepository {

    override suspend fun getMeals(
        meal: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Meals>>> {

        return flow {
            emit(Resource.Loading())

            //local
            val localMeals = dao.getMeals(meal).map { it.toMeals() }
            emit(Resource.Success(data = localMeals))

            val noLocalCache = localMeals.isEmpty()
            val loadFromCache = !noLocalCache && !fetchFromRemote

            if (loadFromCache){
                emit(Resource.Loading())
                return@flow
            }


            try {
                val remoteMeals = apiService.getMeals(meal)
                emit(Resource.Success(remoteMeals.meals.map { it.toMeals() }))
                remoteMeals.meals.map { it.strSource }.let { dao.deleteMeals(it as List<String>) }
                dao.addMeals(remoteMeals.meals.map { it.toMealsEntity() })
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

            val newMeals = dao.getMeals(meal).map { it.toMeals() }
            emit(Resource.Success(newMeals))

        }
    }
}