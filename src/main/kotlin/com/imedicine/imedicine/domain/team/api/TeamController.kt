package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.common.dto.UserIdBody
import com.imedicine.imedicine.domain.team.api.dto.CreateTeamBody
import com.imedicine.imedicine.security.AuthUser
import com.imedicine.imedicine.security.LeaderAuthorize
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
    fun create(@RequestBody body: CreateTeamBody, @AuthenticationPrincipal user: AuthUser): ApiResponse<Unit> =
        ApiResponse.success(teamFacade.create(user, body))

    @Operation(summary = "팀원을 팀에 추가")
    @PutMapping("/{teamId}/add")
    @LeaderAuthorize
    fun addMemberToTeam(
        @PathVariable
        @Schema(description = "team id", example = "1")
        teamId: Long,
        @AuthenticationPrincipal user: AuthUser,
        @RequestBody body: UserIdBody
    ): ApiResponse<Unit> =
        ApiResponse.success(teamFacade.addMemberToTeam(teamId, body))

    @Operation(summary = "팀원을 팀에 제외")
    @PutMapping("/{teamId}/remove")
    @LeaderAuthorize
    fun removeMemberFromTeam(
        @PathVariable
        @Schema(description = "team id", example = "1")
        teamId: Long,
        @AuthenticationPrincipal user: AuthUser,
        @RequestBody body: UserIdBody
    ): ApiResponse<Unit> =
        ApiResponse.success(teamFacade.removeMemberFromTeam(teamId, body))
}