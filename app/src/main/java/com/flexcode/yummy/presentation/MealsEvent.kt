package com.flexcode.yummy.presentation

sealed class MealsEvent {
    data class OnSearchMeal(val meal:String):MealsEvent()
    object Refresh: MealsEvent()
}