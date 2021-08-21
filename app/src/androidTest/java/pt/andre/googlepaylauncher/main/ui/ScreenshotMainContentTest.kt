package pt.andre.googlepaylauncher.main.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import org.junit.*
import pt.andre.googlepaylauncher.utilities.ScreenshotComparator
import pt.andre.googlepaylauncher.utilities.TestCase

class ScreenshotMainContentTest {

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

        ScreenshotComparator.assertScreenshotMatches(
            testCase = TestCase(
                folder = SCREENSHOT_LOCATIONS,
                filename = if (isDarkMode) "dark" else "light"
            ),
            node = composeTestRule.onRoot()
        )
    }
}