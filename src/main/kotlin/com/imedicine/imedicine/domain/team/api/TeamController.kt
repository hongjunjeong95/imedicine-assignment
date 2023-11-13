package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.domain.team.api.dto.TeamDto
import com.imedicine.imedicine.security.AuthUser
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "Team")
@RestController
@RequestMapping("/api/v1/teams")
class TeamController(private val teamFacade: TeamFacade) {
    @Operation(summary = "팀 생성")
    @PostMapping
    fun create(@RequestBody body: TeamDto, @AuthenticationPrincipal user: AuthUser): ApiResponse<Unit> =
        ApiResponse.success(teamFacade.create(user, body))
}