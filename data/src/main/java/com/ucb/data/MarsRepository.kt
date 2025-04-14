package com.ucb.data

import com.ucb.data.mars.IMarsRemoteDataSource
import com.ucb.domain.MarsPhoto


class MarsRepository (
    private val remoteDataSource: IMarsRemoteDataSource
) {
    suspend fun getMarsPhotos(sol: Int, camera: String? = null, page: Int = 1): NetworkResult<List<MarsPhoto>> {
        return remoteDataSource.getMarsPhotos(sol, camera, page)
    }
}