package com.ucb.framework.mappers


import com.ucb.domain.Dog
import com.ucb.framework.dto.DogResponseDto

// Única función de extensión para convertir DogResponseDto a Dog
fun DogResponseDto.toModel(): Dog {
    return Dog(
        imageUrl = this.message,
        status = this.status
    )
}