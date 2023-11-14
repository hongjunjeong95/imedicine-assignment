package com.imedicine.imedicine.domain.teamData.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.domain.teamData.api.dto.CreateTeamDataDto
import com.imedicine.imedicine.domain.teamData.api.dto.TeamDataListResponse
import com.imedicine.imedicine.domain.teamData.api.dto.TeamDataResponse
import com.imedicine.imedicine.security.AuthUser
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@Tag(name = "Team Data")
@RestController
@RequestMapping("/api/v1/team-data")
class TeamDataController(private val teamDataFacade: TeamDataFacade) {
    @Operation(summary = "팀 데이터 생성")
    @PostMapping
    fun create(@RequestBody body: CreateTeamDataDto, @AuthenticationPrincipal user: AuthUser): ApiResponse<Unit> =
        ApiResponse.success(teamDataFacade.create(user, body))

    @Operation(summary = "팀 내 자신의 데이터 리스트 조회 혹은 팀장이 팀 내 모든 데이터 조회")
    @GetMapping
    fun findMany(
        @AuthenticationPrincipal
        user: AuthUser,

        @RequestParam(required = true)
        @Schema(description = "team id", example = "1")
        teamId: Long,
    ): ApiResponse<TeamDataListResponse> =
        ApiResponse.success(TeamDataListResponse.of(teamDataFacade.findTeamDataList(user, teamId)))

    @Operation(summary = "팀 내 자신의 데이터 한 개 조회 혹은 팀장이 팀 내 데이터 조회")
    @GetMapping("/{teamDataId}")
    fun findOne(
        @AuthenticationPrincipal
        user: AuthUser,

        @PathVariable
        @Schema(description = "team data id", example = "1")
        teamDataId: Long
    ): ApiResponse<TeamDataResponse> =
        ApiResponse.success(TeamDataResponse.of(teamDataFacade.findTeamData(teamDataId)))
}