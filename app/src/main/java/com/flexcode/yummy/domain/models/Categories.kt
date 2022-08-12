package com.flexcode.yummy.domain.models

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("idCategory") var idCategory: Int? = null,
    @SerializedName("strCategory") var strCategory: String? = null,
    @SerializedName("strCategoryThumb") var strCategoryThumb: String? = null,
    @SerializedName("strCategoryDescription") var strCategoryDescription: String? = null
)
