package com.example.compose.rally.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.RallyScreen
import org.junit.Rule
import org.junit.Test

class RallyTopAppBarTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        composeTestRule.setContent {
            MaterialTheme {
                RallyTopAppBar(
                    allScreens = RallyScreen.entries,
                    onTabSelected = { },
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .performClick()
    }

    @Test
    fun rallyTopAppBar_selectionTest() {
        var currentScreen by mutableStateOf(RallyScreen.Accounts)

        composeTestRule.setContent {
            MaterialTheme {
                RallyTopAppBar(
                    allScreens = RallyScreen.entries,
                    onTabSelected = { currentScreen = it },
                    currentScreen = currentScreen
                )
            }
        }

        RallyScreen.entries.forEach {
            composeTestRule
                .onNodeWithContentDescription(it.name)
                .performClick()
                .assertIsSelected()
        }
    }
}