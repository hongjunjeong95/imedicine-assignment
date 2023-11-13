package com.imedicine.imedicine.common.dto

import io.swagger.v3.oas.annotations.media.Schema

data class UserIdBody(
    @Schema(description = "user id", example = "1")
    val userId: Long,
)

