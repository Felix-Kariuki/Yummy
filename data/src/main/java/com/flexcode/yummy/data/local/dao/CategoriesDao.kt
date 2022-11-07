package com.flexcode.yummy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.yummy.data.local.entity.CategoriesEntity

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(category: List<CategoriesEntity>)

    @Query("DELETE FROM categoriesentity")
    suspend fun deleteCategories()

    @Query("SELECT * FROM categoriesentity")
    suspend fun getCategories(): List<CategoriesEntity>
}
