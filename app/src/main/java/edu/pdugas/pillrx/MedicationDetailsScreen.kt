package edu.pdugas.pillrx

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MedicationDetailsScreen(
    medication: MedicationResult,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Back Button
        TextButton(
            onClick = onBack
        ) {
            Icon(

                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )

            Spacer(Modifier.width(8.dp))

            Text("Back to Results")
        }
        // Screen title
        Text(
            text = "Medication Details",
            style = MaterialTheme.typography.headlineMedium
        )

        // Selected medication
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Medication,
                    contentDescription = null
                )

                Text(
                    text = medication.name,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = medication.description,
                    style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            MedicationInfoSection(
                title = "Uses",
                information = "Sample information: This medication may be used for pain relief or other conditions depending on the product."
            )
            MedicationInfoSection(
                title = "Warnings",
                information = "Sample information only. Always review the official medication label for warnings and precautions."
            )

            MedicationInfoSection(
                title = "Dosage Information",
                information = "Dosage instructions will be retrieved from the medication label when API integration is implemented."
            )

            Text(
                text = "Disclaimer: This screen currently displays sample information and is not medical advice.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    @Composable
    fun MedicationInfoSection(
        title: String,
        information: String
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = information,
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider()
        }
    }