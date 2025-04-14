package com.ucb.framework.service

import com.ucb.framework.dto.DogResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface IDogApiService {
    @GET("/api/breeds/image/random")
    suspend fun fetchRandomDog(): Response<DogResponseDto>
}