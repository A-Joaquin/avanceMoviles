package com.ucb.ucbtest.mars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.MarsPhoto
import com.ucb.usecases.GetMarsPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MarsGalleryViewModel @Inject constructor(
    private val getMarsPhotos: GetMarsPhotos
) : ViewModel() {
    sealed class UiState {
        object Loading : UiState()
        data class Success(val photos: List<MarsPhoto>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _photos = MutableStateFlow<List<MarsPhoto>>(emptyList())
    val photos: StateFlow<List<MarsPhoto>> = _photos.asStateFlow()

    private var currentPage = 1
    private var currentSol = 1000
    private var currentCamera: String? = null

    init {
        loadPhotos()
    }

    fun loadPhotos(sol: Int = currentSol, camera: String? = currentCamera) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            currentSol = sol
            currentCamera = camera
            currentPage = 1
            try {
                when (val result = getMarsPhotos.invoke(sol, camera, currentPage)) {
                    is NetworkResult.Success -> {
                        _photos.value = result.data
                        _uiState.value = UiState.Success(_photos.value)
                    }
                    is NetworkResult.Error -> {
                        _uiState.value = UiState.Error(result.error)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun loadMorePhotos() {
        viewModelScope.launch {
            try {
                currentPage++
                when (val result = getMarsPhotos.invoke(currentSol, currentCamera, currentPage)) {
                    is NetworkResult.Success -> {
                        _photos.value = _photos.value + result.data
                        _uiState.value = UiState.Success(_photos.value)
                    }
                    is NetworkResult.Error -> {
                        _uiState.value = UiState.Error(result.error)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun filterByCamera(camera: String?) {
        loadPhotos(currentSol, camera)
    }
}