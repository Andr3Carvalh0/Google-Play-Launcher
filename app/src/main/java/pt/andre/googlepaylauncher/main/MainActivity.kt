package pt.andre.googlepaylauncher.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import pt.andre.googlepaylauncher.main.ui.MainContent
import pt.andre.googlepaylauncher.main.vm.MainViewModel
import pt.andre.googlepaylauncher.utilities.ThemeComposition
import pt.andre.googlepaylauncher.utilities.nonNullCollect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.intent.nonNullCollect { intent ->
                startActivity(
                    intent.apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                )
            }
        }

        val composeView = ComposeView(this@MainActivity).apply {
            setContent {
                val hasError = viewModel.error.value

                ThemeComposition {
                    if (hasError) {
                        MainContent()
                    }
                }
            }
        }

        setContentView(composeView)

        viewModel.initialize()
    }
}
