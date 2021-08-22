package pt.andre.googlepaylauncher.main.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import org.junit.AfterClass
import org.junit.Rule
import org.junit.Test
import pt.andre.googlepaylauncher.utilities.ScreenshotComparator
import pt.andre.googlepaylauncher.utilities.TestCase
import pt.andre.googlepaylauncher.utilities.density

class ScreenshotMainContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private val testCase = TestCase(
            folder = "main/$density"
        )

        @AfterClass
        @JvmStatic
        internal fun cleanup() {
            ScreenshotComparator.clean(testCase)
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
            testCase = testCase.copy(
                filename = if (isDarkMode) "dark" else "light"
            ),
            node = composeTestRule.onRoot()
        )
    }
}