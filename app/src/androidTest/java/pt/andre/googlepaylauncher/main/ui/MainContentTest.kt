package pt.andre.googlepaylauncher.main.ui

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.test.core.app.ApplicationProvider
import org.junit.AfterClass
import org.junit.Rule
import org.junit.Test
import pt.andre.googlepaylauncher.R
import pt.andre.googlepaylauncher.utilities.ScreenshotComparator
import pt.andre.googlepaylauncher.utilities.ScreenshotComparator.assertScreenshotMatches
import pt.andre.googlepaylauncher.utilities.TestCase

class MainContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private const val SCREENSHOT_LOCATIONS = "420dpi/main"

        @AfterClass
        @JvmStatic
        fun cleanup() {
            ScreenshotComparator.clean(
                TestCase(folder = SCREENSHOT_LOCATIONS)
            )
        }
    }

    @Test
    fun main_content_renders_correctly() {
        composeTestRule.setContent {
            MainContent()
        }

        val context = ApplicationProvider.getApplicationContext<Context>()
        val expectedText = context.getString(R.string.no_gpay_installed)

        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
    }

    @Test
    fun main_content_light_theme_screenshot_test() {
        main_content_screenshot_test(false)
    }

    @Test
    fun main_content_dark_theme_screenshot_test() {
        main_content_screenshot_test(true)
    }

    private fun main_content_screenshot_test(isDarkMode: Boolean) {
        composeTestRule.setContent {
            MainContent(isDarkMode)
        }

        assertScreenshotMatches(
            testCase = TestCase(
                folder = SCREENSHOT_LOCATIONS,
                filename = if(isDarkMode) "dark" else "light"
            ),
            node = composeTestRule.onRoot()
        )
    }
}