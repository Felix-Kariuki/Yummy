package com.flexcode.yummy.presentation.meal_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexcode.yummy.domain.models.Meals
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MealDetailsScreen(
    meals: Meals,
    navigator: DestinationsNavigator,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        DetailsItem(
            meals = meals,
            navigator = navigator
        )
    }
}
