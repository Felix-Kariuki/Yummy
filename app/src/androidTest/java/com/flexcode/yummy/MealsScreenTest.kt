package com.flexcode.yummy

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.presentation.meals_screen.MealsScreen
import com.flexcode.yummy.ui.theme.YummyTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4ClassRunner::class)
class MealsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val navigator = mock(DestinationsNavigator::class.java)

    @Test
    fun navigateToDetails() {
        val meals = Meals()

        composeTestRule.setContent {
            YummyTheme {
                MealsScreen(navigator = navigator)
            }
        }
        composeTestRule.onNodeWithText("Details").assertExists()
    }
}
