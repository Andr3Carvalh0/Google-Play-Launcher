package pt.andre.googlepaylauncher.utilities

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

internal suspend fun <R> Flow<R?>.nonNullCollect(action: suspend (value: R) -> Unit) {
    this.collect {
        it?.let { data -> action(data) }
    }
}
