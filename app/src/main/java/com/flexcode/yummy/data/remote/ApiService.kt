package com.flexcode.yummy.data.remote

import com.flexcode.yummy.data.dto.CategoryResponse
import com.flexcode.yummy.data.dto.MealResponse
import com.flexcode.yummy.data.dto.MealsDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("json/v1/1/search.php?")
     suspend fun getMeals(@Query("s")meal:String): MealResponse

     @GET("json/v1/1/categories.php")
     suspend fun getCategories():CategoryResponse

    //https://www.themealdb.com/api/json/v1/1/lookup.php?i=52955
     @GET("json/v1/1/lookup.php?")
     suspend fun getMealsInfo(@Query("i")id:Int):MealsDto
}