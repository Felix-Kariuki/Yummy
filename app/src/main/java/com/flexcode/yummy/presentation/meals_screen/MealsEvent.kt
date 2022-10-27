package com.flexcode.yummy.presentation.meals_screen

sealed class MealsEvent {
    data class OnSearchMeal(val meal: String) : MealsEvent()
    object Refresh : MealsEvent()
    object OnClickClearText : MealsEvent()
    data class OnClickCategoryItem(val category: String) : MealsEvent()
}
