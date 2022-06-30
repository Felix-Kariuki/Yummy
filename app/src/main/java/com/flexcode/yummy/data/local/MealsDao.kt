package com.flexcode.yummy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.yummy.data.local.entity.CategoriesEntity
import com.flexcode.yummy.data.local.entity.MealsEntity

@Dao
interface MealsDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun addMeals(meals:List<MealsEntity>)

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertCategories(category:List<CategoriesEntity>)

    @Query("DELETE FROM mealsentity WHERE strSource IN(:meals)")
    suspend fun deleteMeals(meals: List<String>)

    @Query("DELETE FROM categoriesentity")
    suspend fun deleteCategories()

    @Query("SELECT * FROM mealsentity WHERE strMeal || strCategory LIKE '%' || :meal || '%'")
    suspend fun getMeals(meal: String): List<MealsEntity>

    @Query("SELECT * FROM categoriesentity")
    suspend fun getCategories(): List<CategoriesEntity>

}