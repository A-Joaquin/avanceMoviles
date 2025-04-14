package com.ucb.ucbtest.dogsgallery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ucb.domain.Dog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsGalleryUI(viewModel: DogsGalleryViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val dogs by viewModel.dogs.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dogs Gallery") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(dogs) { dog ->
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            AsyncImage(
                                model = dog.imageUrl,
                                contentDescription = "Dog Image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                
                Button(
                    onClick = { viewModel.loadMoreDogs() },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Load More Dogs")
                }
            }
        }
    )
}