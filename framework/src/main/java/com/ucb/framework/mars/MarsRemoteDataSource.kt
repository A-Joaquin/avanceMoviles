package com.ucb.framework.mars

import com.ucb.data.NetworkResult
import com.ucb.domain.MarsPhoto
import com.ucb.framework.dto.MarsPhotoResponseDto
import com.ucb.framework.mappers.toDomain
import com.ucb.framework.service.IMarsApiService
import com.ucb.data.mars.IMarsRemoteDataSource
import com.ucb.framework.service.RetrofitBuilder

class MarsRemoteDataSource(
    private val retrofitService: RetrofitBuilder
) : IMarsRemoteDataSource {
    override suspend fun getMarsPhotos(sol: Int, camera: String?, page: Int): NetworkResult<List<MarsPhoto>> {
        return try {
            val response = retrofitService.marsService.getMarsPhotos(sol, camera, page)
            if (response.isSuccessful) {
                val marsResponse = response.body()
                if (marsResponse != null) {
                    val photos = marsResponse.photos.map { it.toDomain() }
                    NetworkResult.Success(photos)
                } else {
                    NetworkResult.Error("Response body is null")
                }
            } else {
                NetworkResult.Error("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown error occurred")
        }
    }
}