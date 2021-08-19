package pt.andre.googlepaylauncher.utilities

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext

internal val Context.isDarkMode: Boolean
    get() {
        val state = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        return state == Configuration.UI_MODE_NIGHT_YES
    }

data class DarkMode(
    val isDarkMode: Boolean
)

internal val LocalDarkMode = compositionLocalOf { DarkMode(false) }

@Composable
internal fun ThemeComposition(
    content: @Composable () -> Unit
) {
    val isDarkMode = LocalContext.current.isDarkMode

    CompositionLocalProvider(LocalDarkMode provides DarkMode(isDarkMode)) {
        content()
    }
}