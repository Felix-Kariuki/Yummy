package com.flexcode.yummy.data.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.flexcode.yummy.data.local.db.MealsDatabase
import com.flexcode.yummy.data.local.entity.CategoriesEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoriesDaoTest {
    private lateinit var database: MealsDatabase
    private lateinit var categoriesDao: CategoriesDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, MealsDatabase::class.java)
            .allowMainThreadQueries().build()
        categoriesDao = database.categoriesDao
    }

    @After
    fun tearDown() {
        runBlocking {
            categoriesDao.deleteCategories()
            database.close()
        }
    }

    @Test
    fun insertCategories() {
        runBlocking {
            val testCategoryNameOne = "Beef"
            val testCategoryOne = CategoriesEntity(
                strCategory = testCategoryNameOne, idCategory = 1, strCategoryThumb = "beef.png",
                strCategoryDescription = "Beef from cattle"
            )

            val testCategoryNameTwo = "Chicken"
            val testCategoryTwo = CategoriesEntity(
                strCategory = testCategoryNameTwo, idCategory = 2, strCategoryThumb = "chicken.png",
                strCategoryDescription = "Chicken category"
            )

            val testList = listOf(testCategoryOne, testCategoryTwo)
            categoriesDao.insertCategories(testList)

            val result = categoriesDao.getCategories()

            Assert.assertEquals(testList, result)
        }
    }

    @Test
    fun deleteCategories() {
        runBlocking {
            val testCategoryNameOne = "Dessert"
            val testCategoryOne = CategoriesEntity(
                strCategory = testCategoryNameOne, idCategory = 3, strCategoryThumb = "dessert.png",
                strCategoryDescription = "Dessert is a course that concludes a meal"
            )

            val testCategoryNameTwo = "Lamb"
            val testCategoryTwo = CategoriesEntity(
                strCategory = testCategoryNameTwo, idCategory = 4, strCategoryThumb = "Lamb.png",
                strCategoryDescription = "Lamb, hogget, and mutton are the meat of domestic sheep"
            )

            val testList = listOf(testCategoryOne, testCategoryTwo)
            categoriesDao.insertCategories(testList)
            categoriesDao.deleteCategories()

            val result = categoriesDao.getCategories()
            assertThat(result.isEmpty())
        }
    }
}
