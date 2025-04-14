package com.ucb.framework.dog

import com.ucb.data.NetworkResult
import com.ucb.data.dog.IDogRemoteDataSource
import com.ucb.domain.Dog
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitBuilder

class DogRemoteDataSource(
    private val retrofitService: RetrofitBuilder
) : IDogRemoteDataSource {
    override suspend fun fetchRandomDog(): NetworkResult<Dog> {
        return try {
            val response = retrofitService.dogService.fetchRandomDog()
            if (response.isSuccessful) {
                val dogResponse = response.body()
                if (dogResponse != null) {
                    NetworkResult.Success(dogResponse.toModel())
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