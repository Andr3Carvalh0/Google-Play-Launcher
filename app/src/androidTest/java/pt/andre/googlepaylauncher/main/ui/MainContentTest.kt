package pt.andre.googlepaylauncher.main.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
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

        val expectedText = InstrumentationRegistry.getInstrumentation()
            .context
            .getString(R.string.no_gpay_installed)

        composeTestRule.onNodeWithText(
            expectedText
        ).assertIsDisplayed()
    }
}