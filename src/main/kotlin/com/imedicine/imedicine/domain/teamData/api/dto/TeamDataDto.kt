package com.imedicine.imedicine.domain.teamData.api.dto

import io.swagger.v3.oas.annotations.media.Schema

data class CreateTeamDataDto(
    @Schema(description = "Memo", example = "연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀")
    val memo: String,

    @Schema(description = "Team id", example = "1")
    val teamId: Long,
)

