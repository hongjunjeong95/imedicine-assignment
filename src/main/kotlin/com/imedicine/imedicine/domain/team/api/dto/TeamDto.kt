package com.imedicine.imedicine.domain.team.api.dto

import com.imedicine.imedicine.domain.team.persistent.Team
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Page
import java.time.LocalDateTime

data class CreateTeamBodyDto(
    @Schema(description = "팀 이름", example = "연구 1팀")
    val name: String,
)


data class TeamsResponse(
    val items: Page<TeamResponse>
) {
//    fun get(index: Int): TeamResponse = items[index]
    companion object {
        fun of(teams: Page<Team>) = TeamsResponse(teams.map(TeamResponse::of))
    }
}

data class TeamResponse(
    val id: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val name: String,
){
    companion object {
        fun of(team: Team): TeamResponse {
            checkNotNull(team.id)
            return TeamResponse(
                id = team.id,
                createdAt = team.createdAt,
                updatedAt = team.updatedAt,
                name = team.name,
            )
        } }
}