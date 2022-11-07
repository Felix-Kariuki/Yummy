package com.flexcode.yummy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoriesEntity(
    @PrimaryKey var idCategory: Int? = null,
    var strCategory: String? = null,
    var strCategoryThumb: String? = null,
    var strCategoryDescription: String? = null
)
