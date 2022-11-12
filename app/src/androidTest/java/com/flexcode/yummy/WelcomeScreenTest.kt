package com.flexcode.yummy

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun welcome_button_exists() {
        composeTestRule.onNodeWithText("Get Started").assertExists()
    }

    @Test
    fun welcome_button_clicked() {
        composeTestRule.onNodeWithText("Get Started").performClick()
    }
}
