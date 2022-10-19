package com.flexcode.yummy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexcode.yummy.data.local.entity.CategoriesEntity
import com.flexcode.yummy.data.local.entity.MealsEntity

@Database(
    entities = [MealsEntity::class, CategoriesEntity::class], version = 9, exportSchema = false
)
abstract class MealsDatabase : RoomDatabase() {
    abstract val mealsDao: MealsDao

    companion object {
        const val DATABASE_NAME = "meals.db"
    }
}
