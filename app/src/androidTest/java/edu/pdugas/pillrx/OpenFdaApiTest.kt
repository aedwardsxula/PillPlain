package edu.pdugas.pillrx

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenFdaApiTest {

    @Test
    fun fetchLabelsReturnsMedicationRecords() = runBlocking {
        val response = try {
            OpenFdaApi.fetchLabels()
        } catch (failure: Exception) {
            throw AssertionError(
                "API request failed (${failure.javaClass.simpleName}). " +
                        "Check the local API key and emulator internet connection."
            )
        }

        assertTrue(
            "Expected between 1 and 5 medication labels.",
            response.results.size in 1..5
        )

        assertTrue(
            "Every medication label should have an ID.",
            response.results.all { it.id.isNotBlank() }
        )
    }
}