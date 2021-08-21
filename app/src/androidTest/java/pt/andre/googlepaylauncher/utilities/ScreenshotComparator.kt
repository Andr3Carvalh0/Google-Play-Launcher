package pt.andre.googlepaylauncher.utilities

/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import java.io.FileOutputStream

data class TestCase(
    val folder: String,
    val filename: String = ""
)

object ScreenshotComparator {
    internal fun clean(
        testCase: TestCase
    ) {
        val filesDir = InstrumentationRegistry.getInstrumentation().targetContext.filesDir

        with(File(filesDir, testCase.folder)) {
            deleteRecursively()
        }
    }

    /**
     * Simple on-device screenshot comparator that uses golden images present in
     * `androidTest/assets`.
     *
     * Minimum SDK is O. Densities between devices must match.
     *
     * Screenshots are saved on device in `/data/data/{package}/files`.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    internal fun assertScreenshotMatches(
        testCase: TestCase,
        node: SemanticsNodeInteraction
    ) {
        val bitmap = node.captureToImage().asAndroidBitmap()

        // Save screenshot to file for debugging
        saveScreenshot(
            folder = testCase.folder,
            filename = "${testCase.folder.stringSafePathname}_${testCase.filename}_${System.currentTimeMillis()}",
            bitmap = bitmap
        )

        val expectedBitmap = InstrumentationRegistry.getInstrumentation()
            .context.resources.assets.open("${testCase.folder}/${testCase.filename}.png")
            .use { BitmapFactory.decodeStream(it) }

        expectedBitmap.compare(bitmap)
    }
}

private val String.stringSafePathname: String
    get() {
        return replace("/", "_")
    }

private fun saveScreenshot(
    folder: String,
    filename: String,
    bitmap: Bitmap
) {
    val filesDir = InstrumentationRegistry.getInstrumentation().targetContext.filesDir

    val path = File(filesDir, folder).also {
        if (!it.exists()) {
            it.mkdirs()
        }
    }

    FileOutputStream("$path/$filename.png").use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }

    println("Saved screenshot to $path/$filename.png")
}

private fun Bitmap.compare(other: Bitmap) {
    if (this.width != other.width || this.height != other.height) {
        throw AssertionError("Size of screenshot does not match golden file (check device density)")
    }
    // Compare row by row to save memory on device
    val row1 = IntArray(width)
    val row2 = IntArray(width)
    for (column in 0 until height) {
        // Read one row per bitmap and compare
        this.getRow(row1, column)
        other.getRow(row2, column)
        if (!row1.contentEquals(row2)) {
            throw AssertionError("Sizes match but bitmap content has differences")
        }
    }
}

private fun Bitmap.getRow(pixels: IntArray, column: Int) {
    this.getPixels(pixels, 0, width, 0, column, width, 1)
}