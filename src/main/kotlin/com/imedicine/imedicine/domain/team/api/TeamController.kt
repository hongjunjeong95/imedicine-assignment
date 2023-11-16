package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.common.dto.PaginationQuery
import com.imedicine.imedicine.domain.team.api.dto.CreateTeamBodyDto
import com.imedicine.imedicine.domain.team.api.dto.TeamResponse
import com.imedicine.imedicine.domain.team.api.dto.TeamsResponse
import com.imedicine.imedicine.security.AuthUser
import com.imedicine.imedicine.security.LeaderAndMemberAuthorize
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@Tag(name = "Team")
@RestController
@RequestMapping("/api/v1/teams")
class TeamController(private val teamFacade: TeamFacade) {
    @Operation(summary = "팀 생성")
    @PostMapping
    fun create(@RequestBody body: CreateTeamBodyDto, @AuthenticationPrincipal user: AuthUser): ApiResponse<Unit> =
        ApiResponse.success(teamFacade.createTeam(user.id, body))

    @Operation(summary = "팀 리스트 조회")
    @GetMapping
    fun findMany(
        query: PaginationQuery
    ): ApiResponse<TeamsResponse> =
        ApiResponse.success(TeamsResponse.of(teamFacade.findTeams(query)))

    @Operation(summary = "팀 조회")
    @GetMapping("/{teamId}")
    @LeaderAndMemberAuthorize
    fun findOne(
        @AuthenticationPrincipal
        user: AuthUser,

        @PathVariable
        @Schema(description = "team data id", example = "1")
        teamId: Long
    ): ApiResponse<TeamResponse> =
        ApiResponse.success(TeamResponse.of(teamFacade.findTeam(teamId)))

}