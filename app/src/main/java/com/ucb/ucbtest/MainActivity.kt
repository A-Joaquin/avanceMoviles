package com.ucb.ucbtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ucb.ucbtest.dogsgallery.DogsGalleryUI
import com.ucb.ucbtest.mars.HomeScreen
import com.ucb.ucbtest.mars.MarsGalleryScreen
import com.ucb.ucbtest.ui.theme.UcbtestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ucbtest)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UcbtestTheme {
                DogsGalleryUI()
//                var selectedCamera by remember { mutableStateOf<String?>(null) }
//                if (selectedCamera == null) {
//                    HomeScreen(onNavigateToCamera = { camera -> selectedCamera = camera })
//                } else {
//                    MarsGalleryScreen(selectedCamera = selectedCamera)
//                }
            }
        }
    }
}