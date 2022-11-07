package com.flexcode.yummy.data.remote.mapper

import com.flexcode.yummy.data.local.entity.CategoriesEntity
import com.flexcode.yummy.data.remote.dto.CategoriesDto
import com.flexcode.yummy.domain.models.Categories

fun CategoriesEntity.toCategories(): Categories {
    return Categories(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryThumb = strCategoryThumb,
        strCategoryDescription = strCategoryDescription
    )
}

fun CategoriesDto.toCategoriesEntity(): CategoriesEntity {
    return CategoriesEntity(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryThumb = strCategoryThumb,
        strCategoryDescription = strCategoryDescription
    )
}

fun CategoriesDto.toCategories(): Categories {
    return Categories(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryThumb = strCategoryThumb,
        strCategoryDescription = strCategoryDescription
    )
}
