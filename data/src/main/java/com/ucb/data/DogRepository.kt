package com.ucb.data

import com.ucb.data.dog.IDogRemoteDataSource
import com.ucb.domain.Dog

class DogRepository(
    private val remoteDataSource: IDogRemoteDataSource
) {
    suspend fun getRandomDog(): NetworkResult<Dog> {
        return remoteDataSource.fetchRandomDog()
    }
}