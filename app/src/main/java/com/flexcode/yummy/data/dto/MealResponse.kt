package com.flexcode.yummy.data.dto

import java.io.Serializable

data class MealResponse(
    val meals: ArrayList<MealsDto>?
) : Serializable
