package com.imedicine.imedicine.domain.member.api.dto

import io.swagger.v3.oas.annotations.media.Schema

data class AddMemberBody(
    @Schema(description = "team id", example = "1")
    val teamId: Long,

    @Schema(description = "user id", example = "1")
    val userId: Long,
)

data class RemoveMemberBody(
    @Schema(description = "team id", example = "1")
    val teamId: Long,

    @Schema(description = "user id", example = "1")
    val userId: Long,
)