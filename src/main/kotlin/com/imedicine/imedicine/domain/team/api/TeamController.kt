package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.common.dto.PaginationQuery
import com.imedicine.imedicine.domain.team.api.dto.CreateTeamBodyDto
import com.imedicine.imedicine.domain.team.api.dto.TeamsResponse
import com.imedicine.imedicine.security.AuthUser
import io.swagger.v3.oas.annotations.Operation
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
}