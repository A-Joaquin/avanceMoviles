package com.ucb.framework.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarsPhotoResponseDto(
    val photos: List<MarsPhotoDto>
)
@JsonClass(generateAdapter = true)
data class MarsPhotoDto(
    val id: Int,
    val sol: Int,
    val camera: MarsCameraDto,
    val imgSrc: String,
    val earthDate: String,
    val rover: MarsRoverDto
)
@JsonClass(generateAdapter = true)
data class MarsCameraDto(
    val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String
)
@JsonClass(generateAdapter = true)
data class MarsRoverDto(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String
)