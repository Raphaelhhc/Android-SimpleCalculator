package com.example.simplecalculator

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.simplecalculator.ui.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun additionUpdatesResult() {
        composeTestRule.onNodeWithTag("btn_1").performClick()
        composeTestRule.onNodeWithTag("btn_+").performClick()
        composeTestRule.onNodeWithTag("btn_2").performClick()
        composeTestRule.onNodeWithTag("btn_=").performClick()
        composeTestRule.onNodeWithTag("resultText").assertTextEquals("Result:\n3.0")
    }

    @Test
    fun clearButtonResetsState() {
        composeTestRule.onNodeWithTag("btn_1").performClick()
        composeTestRule.onNodeWithTag("btn_+").performClick()
        composeTestRule.onNodeWithTag("btn_2").performClick()
        composeTestRule.onNodeWithTag("btn_C").performClick()
        composeTestRule.onNodeWithTag("formulaText").assertTextEquals("Formula:\n")
        composeTestRule.onNodeWithTag("resultText").assertTextEquals("Result:\n")
    }

}