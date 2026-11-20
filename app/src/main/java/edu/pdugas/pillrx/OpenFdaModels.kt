package edu.pdugas.pillrx

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenFdaResponse(
    val results: List<DrugLabel> = emptyList()
)

@Serializable
data class DrugLabel(
    val id: String = "",
    val openfda: OpenFdaNames = OpenFdaNames(),
    val purpose: List<String> = emptyList(),

    @SerialName("indications_and_usage")
    val indicationsAndUsage: List<String> = emptyList(),

    val warnings: List<String> = emptyList(),

    @SerialName("warnings_and_cautions")
    val warningsAndCautions: List<String> = emptyList(),

    @SerialName("boxed_warning")
    val boxedWarning: List<String> = emptyList(),

    @SerialName("dosage_and_administration")
    val dosageAndAdministration: List<String> = emptyList()
)
@Serializable
data class OpenFdaNames(
    @SerialName("brand_name")
    val brandName: List<String> = emptyList(),

    @SerialName("generic_name")
    val genericName: List<String> = emptyList()
)