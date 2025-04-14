package com.ucb.data.dog

import com.ucb.domain.Dog
import com.ucb.data.NetworkResult

interface IDogRemoteDataSource {
    suspend fun fetchRandomDog(): NetworkResult<Dog>
}