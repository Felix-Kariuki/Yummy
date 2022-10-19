package com.flexcode.yummy.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryResponse(
    @SerializedName("categories") var categories: ArrayList<CategoriesDto> = arrayListOf()
) : Serializable
