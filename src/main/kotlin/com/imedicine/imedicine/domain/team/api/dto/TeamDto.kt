package com.imedicine.imedicine.domain.team.api.dto

import io.swagger.v3.oas.annotations.media.Schema

data class CreateTeamDto(
    @Schema(description = "팀 이름", example = "연구 1팀")
    val name: String,
)