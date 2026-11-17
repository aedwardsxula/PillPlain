package edu.pdugas.pillrx

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class MedicationResult(
    val name: String,
    val description: String
)

@Composable
fun MedicationResultsScreen(
    medicationName: String,
    onViewDetails: (MedicationResult) -> Unit = {}
) {
    //Temporary Sample Data: Task 6

    val sampleMedications = listOf(
        MedicationResult(
            "Ibuprofen 200 mg",
            "Commonly used to relieve minor pain and reduce fever."
        ),
        MedicationResult(
            "Ibuprofen 400 mg",
            "A prescription-strength formulation of ibuprofen."
        ),
        MedicationResult(
            "Acetaminophen 500 mg",
            "Commonly used to relieve pain and reduce fever."
        )
    )

    val results = sampleMedications.filter {
        it.name.contains(medicationName.trim(), ignoreCase = true) &&
                medicationName.isNotBlank()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Medication Results",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Search results for \"$medicationName\""
        )

        if (results.isEmpty()) {
            Text(
                text = if (medicationName.isBlank()) {
                    "Search for a medication from the Home screen."
                } else {
                    "No sample results found for \"$medicationName\"."
                }
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(results) { medication ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Medication,
                                    contentDescription = null
                                )

                                Spacer(Modifier.width(8.dp))

                                Text(
                                    text = medication.name,
                                    style =
                                        MaterialTheme.typography.titleMedium
                                )
                            }

                            Text(medication.description)

                            Button(
                                onClick = {
                                    onViewDetails(medication)
                                }
                            ) {
                                Text("View Details")
                            }
                        }
                    }
                }
            }
        }
    }
}