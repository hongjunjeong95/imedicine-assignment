package com.imedicine.imedicine.domain.team.api.dto

import io.swagger.v3.oas.annotations.media.Schema

data class AddMemberToTeamBody(
    @Schema(description = "user id", example = "1")
    val userId: Long,
)

