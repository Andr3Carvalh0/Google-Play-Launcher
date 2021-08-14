package pt.andre.googlepaylauncher.main.ui

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import pt.andre.googlepaylauncher.R

class MainContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun main_content_renders_correctly() {
        composeTestRule.setContent {
            MainContent()
        }

        val context = ApplicationProvider.getApplicationContext<Context>()
        val expectedText = context.getString(R.string.no_gpay_installed)

        composeTestRule.onNodeWithText(
            expectedText
        ).assertIsDisplayed()
    }
}