package com.ucb.usecases

import com.ucb.data.DogRepository
import com.ucb.data.NetworkResult
import com.ucb.domain.Dog

class GetRandomDog(
    private val dogRepository: DogRepository
) {
    suspend fun invoke(): NetworkResult<Dog> {
        return dogRepository.getRandomDog()
    }
}