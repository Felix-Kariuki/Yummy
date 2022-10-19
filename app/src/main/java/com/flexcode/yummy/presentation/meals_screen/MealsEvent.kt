package com.flexcode.yummy.presentation.meals_screen

sealed class MealsEvent {
    data class OnSearchMeal(val meal: String) : MealsEvent()
    object Refresh : MealsEvent()
}
