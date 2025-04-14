package com.ucb.ucbtest.mars

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToCamera: (String?) -> Unit
) {
    val cameras = listOf(
        null to "Todas las Cámaras",
        "FHAZ" to "Cámara Frontal",
        "RHAZ" to "Cámara Trasera",
        "MAST" to "Cámara Mástil",
        "CHEMCAM" to "Cámara Química",
        "MAHLI" to "Cámara de Mano",
        "MARDI" to "Cámara Descenso",
        "NAVCAM" to "Cámara de Navegación"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Galería de Fotos de Marte",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(cameras) { (camera, name) ->
                ElevatedButton(
                    onClick = { onNavigateToCamera(camera) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}