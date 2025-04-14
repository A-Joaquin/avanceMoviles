package com.ucb.data.mars

import com.ucb.data.NetworkResult
import com.ucb.domain.MarsPhoto

interface IMarsRemoteDataSource {
    suspend fun getMarsPhotos(sol: Int, camera: String?, page: Int): NetworkResult<List<MarsPhoto>>
}