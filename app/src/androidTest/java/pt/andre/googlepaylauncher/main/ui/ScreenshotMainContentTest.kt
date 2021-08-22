package pt.andre.googlepaylauncher.main.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import org.junit.*
import pt.andre.googlepaylauncher.utilities.ScreenshotComparator
import pt.andre.googlepaylauncher.utilities.TestCase
import pt.andre.googlepaylauncher.utilities.density

class ScreenshotMainContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private const val SCREENSHOT_LOCATIONS = "main"

        private fun folder() = "$SCREENSHOT_LOCATIONS/$density"

        @AfterClass
        @JvmStatic
        internal fun cleanup() {
            ScreenshotComparator.clean(
                TestCase(folder = folder())
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
                folder = folder(),
                filename = if (isDarkMode) "dark" else "light"
            ),
            node = composeTestRule.onRoot()
        )
    }
}