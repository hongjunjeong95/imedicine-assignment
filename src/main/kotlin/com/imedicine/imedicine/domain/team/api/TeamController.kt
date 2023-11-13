package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.domain.team.api.dto.AddMemberToTeamBody
import com.imedicine.imedicine.domain.team.api.dto.CreateTeamBody
import com.imedicine.imedicine.security.AuthUser
import com.imedicine.imedicine.security.LeaderAuthorize
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
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
        @PathVariable teamId: Long,
        @AuthenticationPrincipal user: AuthUser,
        @RequestBody body: AddMemberToTeamBody
    ): ApiResponse<Unit> =
        ApiResponse.success(teamFacade.addMemberToTeam(teamId, body))
}