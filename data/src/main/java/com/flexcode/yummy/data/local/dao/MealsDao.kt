package com.flexcode.yummy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.yummy.data.local.entity.MealsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeals(meals: List<MealsEntity>)

    @Query("DELETE FROM mealsentity WHERE strSource IN(:meals)")
    suspend fun deleteMeals(meals: List<String>)

    @Query("SELECT * FROM mealsentity WHERE strMeal || strCategory LIKE '%' || :meal || '%'")
    suspend fun getMeals(meal: String): List<MealsEntity>

    @Query("SELECT * FROM mealsentity WHERE strCategory LIKE :category")
    fun getMealsByCategory(category: String): Flow<List<MealsEntity>>
}
