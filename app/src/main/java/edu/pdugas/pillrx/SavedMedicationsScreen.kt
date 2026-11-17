package edu.pdugas.pillrx

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SavedMedicationsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Saved Medications",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "No saved medications yet.",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Your saved medications will appear here.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}