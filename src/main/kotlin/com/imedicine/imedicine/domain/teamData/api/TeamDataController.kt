package com.imedicine.imedicine.domain.teamData.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.domain.teamData.api.dto.CreateTeamDataBodyDto
import com.imedicine.imedicine.domain.teamData.api.dto.TeamDataListResponse
import com.imedicine.imedicine.domain.teamData.api.dto.TeamDataResponse
import com.imedicine.imedicine.domain.teamData.api.dto.UpdateTeamDataBodyDto
import com.imedicine.imedicine.security.AuthUser
import com.imedicine.imedicine.security.LeaderAndMemberAuthorize
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@Tag(name = "Team Data")
@RestController
@RequestMapping("/api/v1/teams/{teamId}/team-data")
@LeaderAndMemberAuthorize
class TeamDataController(private val teamDataFacade: TeamDataFacade) {
    @Operation(summary = "팀 데이터 생성")
    @PostMapping
    fun create(
        @AuthenticationPrincipal
        user: AuthUser,

        @PathVariable(required = true)
        @Schema(description = "team id", example = "1")
        teamId: Long,

        @RequestBody
        body: CreateTeamDataBodyDto,
    ): ApiResponse<Unit> =
        ApiResponse.success(teamDataFacade.createTeamData(user.id, body.teamId, body))

    @Operation(summary = "팀 내 팀장 혹은 자신의 데이터 리스트 조회")
    @GetMapping
    fun findMany(
        @AuthenticationPrincipal
        user: AuthUser,

        @PathVariable(required = true)
        @Schema(description = "team id", example = "1")
        teamId: Long,
    ): ApiResponse<TeamDataListResponse> =
        ApiResponse.success(TeamDataListResponse.of(teamDataFacade.findTeamDataList(user, teamId)))

    @Operation(summary = "팀 내 팀장 혹은 자신의 데이터 한 개 조회")
    @GetMapping("/{teamDataId}")
    fun findOne(
        @PathVariable(required = true)
        @Schema(description = "team id", example = "1")
        teamId: Long,

        @PathVariable
        @Schema(description = "team data id", example = "1")
        teamDataId: Long
    ): ApiResponse<TeamDataResponse> =
        ApiResponse.success(TeamDataResponse.of(teamDataFacade.findTeamData(teamDataId)))

    @Operation(summary = "팀 내 팀장 혹은 자신의 데이터 한 개 수정")
    @PutMapping("/{teamDataId}")
    fun update(
        @PathVariable(required = true)
        @Schema(description = "team id", example = "1")
        teamId: Long,

        @PathVariable
        @Schema(description = "team data id", example = "1")
        teamDataId: Long,

        @RequestBody
        body: UpdateTeamDataBodyDto
    ): ApiResponse<Unit> =
        ApiResponse.success(teamDataFacade.updateTeamData(teamDataId, body))


    @Operation(summary = "팀 내 팀장 혹은 자신의 데이터 한 개 삭제")
    @DeleteMapping("/{teamDataId}")
    fun delete(
        @PathVariable(required = true)
        @Schema(description = "team id", example = "1")
        teamId: Long,

        @PathVariable
        @Schema(description = "team id", example = "1")
        teamDataId: Long
    ): ResponseEntity<Unit> {
        teamDataFacade.deleteTeamData(teamDataId)
        return noContent().build()
    }
}