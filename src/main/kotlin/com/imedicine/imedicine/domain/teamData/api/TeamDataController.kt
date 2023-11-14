package com.imedicine.imedicine.domain.teamData.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.domain.teamData.api.dto.CreateTeamDataDto
import com.imedicine.imedicine.security.AuthUser
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "Team Data")
@RestController
@RequestMapping("/api/v1/team-data")
class TeamDataController(private val teamDataFacade: TeamDataFacade) {
    @Operation(summary = "팀 데이터 생성")
    @PostMapping
    fun create(@RequestBody body: CreateTeamDataDto, @AuthenticationPrincipal user: AuthUser): ApiResponse<Unit> =
        ApiResponse.success(teamDataFacade.create(user, body))
}