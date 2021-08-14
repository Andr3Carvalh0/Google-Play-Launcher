package pt.andre.googlepaylauncher.utilities

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class FlowUtilitiesTest {

    @Test
    fun `flow value when is null nonNullCollect wont call the action`() = runBlocking {
        var value = -1

        flow<Int?> {
            emit(null)
        }.nonNullCollect {
            value = it
        }

        Assert.assertEquals(value, -1)
    }

    @Test
    fun `flow value when is not null nonNullCollect calls action`() = runBlocking {
        var value = -1

        flow {
            (1..2).forEach {
                emit(if (it % 2 == 0) it else null)
                delay(500)
            }
        }.nonNullCollect {
            value = it
        }

        Assert.assertEquals(value, 2)
    }
}
