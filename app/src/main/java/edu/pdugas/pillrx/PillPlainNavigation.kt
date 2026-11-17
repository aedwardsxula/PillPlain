package edu.pdugas.pillrx

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private enum class Destination(val title: String) {
    HOME("Home"),
    SEARCH("Search"),
    SAVED("Saved Medications"),
    INFO("Information"),
    DETAILS("Medication Details")

}

@Composable
fun PillPlainApp() {
    var destination by rememberSaveable { mutableStateOf(Destination.HOME) }
    var savedMedications by androidx.compose.runtime.remember {
        mutableStateOf(emptyList<MedicationResult>())
    }
    var searchedMedication by rememberSaveable {
        mutableStateOf("")
    }
    BackHandler(enabled = destination != Destination.HOME) {
        destination = Destination.HOME
    }
    var selectedMedicationName by rememberSaveable {
        mutableStateOf("")
    }

    var selectedMedicationDescription by rememberSaveable {
        mutableStateOf("")
    }

    BackHandler(enabled = destination != Destination.HOME) {
        destination = if (destination == Destination.DETAILS) {
            Destination.SEARCH
        } else {
            Destination.HOME
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                Destination.entries
                    .filter { it != Destination.DETAILS }
                    .forEach { tab ->
                    val (label, icon) = when (tab) {
                        Destination.HOME -> "Home" to Icons.Default.Home
                        Destination.SEARCH -> "Search" to Icons.Default.Search
                        Destination.SAVED -> "Saved" to Icons.Default.Bookmark
                        Destination.INFO -> "Info" to Icons.Default.Info
                        Destination.DETAILS -> "Details" to Icons.Default.Info

                    }
                        NavigationBarItem(
                            selected = destination == tab,
                            onClick = {
                                destination = tab
                            },
                            icon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(label)
                            }
                        )
                    }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (destination) {

                Destination.HOME -> PillPlainHome(
                    onOpenSaved = {
                        destination = Destination.SAVED
                    },
                    onOpenInfo = {
                        destination = Destination.INFO
                    },
                    onSearchMedication = { medication ->
                        searchedMedication = medication
                        destination = Destination.SEARCH
                    }
                )

                Destination.SEARCH -> MedicationResultsScreen(
                    medicationName = searchedMedication,
                    onViewDetails = { medication ->
                        selectedMedicationName = medication.name
                        selectedMedicationDescription =
                            medication.description
                        destination = Destination.DETAILS
                    }
                )

                Destination.DETAILS -> MedicationDetailsScreen(
                    medication = MedicationResult(
                        name = selectedMedicationName,
                        description = selectedMedicationDescription
                    ),
                    onBack = {
                        destination = Destination.SEARCH
                    },
                    onSave = { medication ->
                        if (savedMedications.none {
                                it.name.equals(medication.name, ignoreCase = true)
                            }) {
                            savedMedications = savedMedications + medication
                        }
                        destination = Destination.SAVED
                    }
                )

                Destination.SAVED -> SavedMedicationsScreen(
                    medications = savedMedications
                )
                Destination.INFO -> Text(destination.title)
            }
        }
    }
}