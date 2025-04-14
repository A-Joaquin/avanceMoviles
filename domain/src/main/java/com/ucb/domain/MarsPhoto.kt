package com.ucb.domain

data class MarsPhoto(
    val id: Int,
    val sol: Int,
    val camera: MarsCamera,
    val imgSrc: String,
    val earthDate: String,
    val rover: MarsRover
)

data class MarsCamera(
    val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String
)

data class MarsRover(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String
)