package com.imedicine.imedicine.domain.teamData.api.dto

import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CreateTeamDataBodyDto(
    @Schema(description = "Memo", example = "연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀")
    val memo: String,

    @Schema(description = "Team id", example = "1")
    val teamId: Long,
)

data class TeamDataListResponse(
    val items: List<TeamDataResponse>
) {
    fun get(index: Int): TeamDataResponse = items[index]
    companion object {
        fun of(teamDataList: List<TeamData>) = TeamDataListResponse(teamDataList.map(TeamDataResponse::of))
    }
}

data class TeamDataResponse(
    val id: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val memo: String,
){
    companion object {
        fun of(teamData: TeamData): TeamDataResponse {
            checkNotNull(teamData.id)
            return TeamDataResponse(
                id = teamData.id,
                createdAt = teamData.createdAt,
                updatedAt = teamData.updatedAt,
                memo = teamData.memo,
            )
        } }
}

data class UpdateTeamDataBodyDto(
    @Schema(description = "Memo", example = "연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀연구 1팀")
    val memo: String,
)