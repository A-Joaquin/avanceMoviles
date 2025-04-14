package com.ucb.usecases

import com.ucb.data.MarsRepository
import com.ucb.data.NetworkResult
import com.ucb.domain.MarsPhoto

class GetMarsPhotos (
    private val marsRepository: MarsRepository
) {
    suspend operator fun invoke(sol: Int, camera: String? = null, page: Int = 1): NetworkResult<List<MarsPhoto>> {
        return marsRepository.getMarsPhotos(sol, camera, page)
    }
}