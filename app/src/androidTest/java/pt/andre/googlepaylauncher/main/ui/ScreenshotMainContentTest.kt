package pt.andre.googlepaylauncher.main.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.*
import pt.andre.googlepaylauncher.utilities.ScreenshotComparator
import pt.andre.googlepaylauncher.utilities.TestCase
import java.io.IOException

/*
* This was just a test and I really didnt care much about escalability.
* That is... The ScreenshotComparator, literally compares and expects that every pixel to be pixel perfect.
* Even on 2 machines running the same Android Device image, might pass on one vs the other due to roundings
* from the GPU. If you want solutions to cover that, you can check Facebooks' screenshot testing for android for example.
* */
class ScreenshotMainContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private const val SCREENSHOT_LOCATIONS = "420dpi/main"
        private var hostType: String? = null

        @AfterClass
        @JvmStatic
        fun cleanup() {
            ScreenshotComparator.clean(
                TestCase(folder = SCREENSHOT_LOCATIONS)
            )
        }

        @BeforeClass
        @JvmStatic
        fun initializeTests() {
            try {
                hostType = InstrumentationRegistry.getInstrumentation()
                    .context.resources.assets.open("details.txt")
                    .bufferedReader()
                    .use { it.readText().trim() }
            } catch (e: IOException) { }
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
        hostType?.let {
            composeTestRule.setContent {
                MainContent(isDarkMode)
            }

            ScreenshotComparator.assertScreenshotMatches(
                testCase = TestCase(
                    folder = "$SCREENSHOT_LOCATIONS/$it",
                    filename = if (isDarkMode) "dark" else "light"
                ),
                node = composeTestRule.onRoot()
            )
        }
    }
}