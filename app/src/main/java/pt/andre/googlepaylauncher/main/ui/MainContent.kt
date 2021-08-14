package pt.andre.googlepaylauncher.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pt.andre.googlepaylauncher.R

@Composable
private fun color(id: Int): Color {
    return Color(
        LocalContext.current.getColor(id)
    )
}

@Composable
internal fun MainContent() {
    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color(id = R.color.backgroundColor)),
        contentAlignment = Alignment.Center
    ) {
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
