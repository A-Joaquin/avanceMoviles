package com.ucb.framework.mappers

import com.ucb.domain.MarsCamera
import com.ucb.domain.MarsPhoto
import com.ucb.domain.MarsRover
import com.ucb.framework.dto.MarsCameraDto
import com.ucb.framework.dto.MarsPhotoDto
import com.ucb.framework.dto.MarsRoverDto

fun MarsPhotoDto.toDomain(): MarsPhoto = MarsPhoto(
    id = id,
    sol = sol,
    camera = camera.toDomain(),
    imgSrc = imgSrc,
    earthDate = earthDate,
    rover = rover.toDomain()
)

fun MarsCameraDto.toDomain(): MarsCamera = MarsCamera(
    id = id,
    name = name,
    roverId = roverId,
    fullName = fullName
)

fun MarsRoverDto.toDomain(): MarsRover = MarsRover(
    id = id,
    name = name,
    landingDate = landingDate,
    launchDate = launchDate,
    status = status
)