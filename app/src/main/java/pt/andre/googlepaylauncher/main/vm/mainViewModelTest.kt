package pt.andre.googlepaylauncher.main.vm

import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import pt.andre.googlepaylauncher.BuildConfig
import pt.andre.googlepaylauncher.main.apps.ApplicationManager
import javax.inject.Inject

internal const val GOOGLE_PAY_PACKAGE = BuildConfig.GOOGLE_PAY_PACKAGE_ID

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val applicationManager: ApplicationManager
) : ViewModel() {

    private val _intent: MutableStateFlow<Intent?> = MutableStateFlow(null)
    internal val intent: Flow<Intent?> = _intent

    private val _error: MutableState<Boolean> = mutableStateOf(false)
    internal val error: State<Boolean> = _error

    internal fun initialize() {
        val entryPoint = applicationManager.getApplicationStartIntent(GOOGLE_PAY_PACKAGE)

        if (entryPoint != null) {
            _intent.value = entryPoint
        } else {
            _error.value = true
        }
    }
}
