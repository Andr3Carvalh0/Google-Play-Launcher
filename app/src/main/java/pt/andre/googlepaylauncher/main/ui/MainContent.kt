package pt.andre.googlepaylauncher.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.andre.googlepaylauncher.R
import pt.andre.googlepaylauncher.utilities.LocalDarkMode

@Composable
private fun color(id: Int): Color {
    return Color(
        LocalContext.current.getColor(id)
    )
}

@Composable
private fun iconTint(
    isDarkMode: Boolean
): Color {
    val color = if (isDarkMode) {
        R.color.iconDarkTint
    } else {
        R.color.iconLightTint
    }

    return color(color)
}

@Composable
internal fun MainContent(
    isDarkMode: Boolean = LocalDarkMode.current.isDarkMode
) {
    return Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color(id = R.color.backgroundColor)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "",
            tint = iconTint(isDarkMode),
            modifier = Modifier.size(72.dp)
        )
        
        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = stringResource(R.string.no_gpay_installed),
            color = color(id = R.color.textColor)
        )
    }
}

@Composable
@Preview
private fun MainContentPreview() {
    MainContent()
}
