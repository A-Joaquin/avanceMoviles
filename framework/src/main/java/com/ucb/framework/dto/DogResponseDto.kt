package com.ucb.framework.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogResponseDto(
    val message: String,
    val status: String
)