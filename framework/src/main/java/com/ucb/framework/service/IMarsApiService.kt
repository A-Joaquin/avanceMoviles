package com.ucb.framework.service

import com.ucb.framework.dto.MarsPhotoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMarsApiService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsPhotos(
        @Query("sol") sol: Int,
        @Query("camera") camera: String?,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "RQ35way94NOFxYiqChqw4bGaRWkmEyh3CJyGY5tO"
    ): Response<MarsPhotoResponseDto>
}