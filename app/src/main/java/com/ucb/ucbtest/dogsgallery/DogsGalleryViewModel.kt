package com.ucb.ucbtest.dogsgallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Dog
import com.ucb.usecases.GetRandomDog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DogsGalleryViewModel @Inject constructor(
    private val getRandomDog: GetRandomDog
) : ViewModel() {
    sealed class UiState {
        object Loading : UiState()
        data class Success(val dogs: List<Dog>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs: StateFlow<List<Dog>> = _dogs.asStateFlow()

    init {
        loadMoreDogs()
    }

    fun loadMoreDogs() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val newDogs = List(10) {
                    when (val result = getRandomDog.invoke()) {
                        is NetworkResult.Success -> result.data
                        is NetworkResult.Error -> null
//                        is NetworkResult.Exception -> null
                    }
                }.filterNotNull()

                _dogs.value = _dogs.value + newDogs
                _uiState.value = UiState.Success(_dogs.value)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}