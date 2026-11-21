package edu.pdugas.pillrx

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


object OpenFdaApi {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 15_000
            socketTimeoutMillis = 30_000
        }
    }

    suspend fun fetchLabels(
        searchExpression: String = "",
        limit: Int = 5
    ): OpenFdaResponse {

        val apiKey = BuildConfig.OPENFDA_API_KEY.trim()

        check(apiKey.isNotEmpty()) {
            "Add OPENFDA_API_KEY to local.properties and rebuild."
        }

        require(limit in 1..1000) {
            "Limit must be between 1 and 1000."
        }

        val response = client.get(
            "https://api.fda.gov/drug/label.json"
        ) {
            parameter("api_key", apiKey)
            if (searchExpression.isNotBlank()) {
                parameter("search", searchExpression)
            }
            parameter("limit", limit)
        }

        check(response.status.value in 200..299) {
            "openFDA request failed with HTTP ${response.status.value}."
        }

        return response.body<OpenFdaResponse>()
    }

    suspend fun searchGenericMedication(
        medicationName: String
    ): OpenFdaResponse {

        val name = medicationName.trim()

        require(name.isNotEmpty()) {
            "Medication name cannot be empty."
        }

        val searchExpression =
            "openfda.generic_name:\"${name.replace("\"", "\\\"")}\""

        return fetchLabels(
            searchExpression = searchExpression,
            limit = 10
        )
    }

        suspend fun searchBrandMedication(
            medicationName: String
        ): OpenFdaResponse {
            val name = medicationName.trim()

            require(name.isNotEmpty()) {
                "Medication name cannot be empty."
            }
            val searchExpression =
                "openfda.brand_name:\"${name.replace("\"", "\\\"")}\""
            return fetchLabels(
                searchExpression = searchExpression,
                limit = 10
            )
        }
    }