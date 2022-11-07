package com.flexcode.yummy.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoriesDto(

    @SerializedName("idCategory") var idCategory: Int? = null,
    @SerializedName("strCategory") var strCategory: String? = null,
    @SerializedName("strCategoryThumb") var strCategoryThumb: String? = null,
    @SerializedName("strCategoryDescription") var strCategoryDescription: String? = null

)
