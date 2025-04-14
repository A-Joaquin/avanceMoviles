package com.ucb.ucbtest.mars

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ucb.domain.MarsPhoto

@Composable
fun MarsGalleryScreen(
    selectedCamera: String? = null,
    viewModel: MarsGalleryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val photos by viewModel.photos.collectAsState()

    LaunchedEffect(selectedCamera) {
        viewModel.filterByCamera(selectedCamera)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CameraFilterChips(
            onCameraSelected = { camera -> viewModel.filterByCamera(camera) },
            modifier = Modifier.padding(8.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState) {
                is MarsGalleryViewModel.UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is MarsGalleryViewModel.UiState.Success -> {
                    PhotosGrid(
                        photos = photos,
                        onLoadMore = { viewModel.loadMorePhotos() }
                    )
                }
                is MarsGalleryViewModel.UiState.Error -> {
                    Text(
                        text = (uiState as MarsGalleryViewModel.UiState.Error).message,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun CameraFilterChips(
    onCameraSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val cameras = listOf(
        null to "Todas",
        "FHAZ" to "FHAZ",
        "RHAZ" to "RHAZ",
        "MAST" to "MAST",
        "CHEMCAM" to "CHEMCAM",
        "MAHLI" to "MAHLI",
        "MARDI" to "MARDI",
        "NAVCAM" to "NAVCAM"
    )
    var selectedCamera by remember { mutableStateOf<String?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cameras) { (camera, name) ->
            FilterChip(
                selected = camera == selectedCamera,
                onClick = {
                    selectedCamera = camera
                    onCameraSelected(camera)
                },
                label = { Text(name) }
            )
        }
    }
}

@Composable
fun PhotosGrid(
    photos: List<MarsPhoto>,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(photos) { photo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                AsyncImage(
                    model = photo.imgSrc,
                    contentDescription = "Mars photo from ${photo.camera.name} camera",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        item {
            if (photos.isNotEmpty()) {
                Button(
                    onClick = onLoadMore,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Cargar más fotos")
                }
            }
        }
    }
}