package com.imedicine.imedicine.domain.member.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.domain.member.api.dto.AddMemberBody
import com.imedicine.imedicine.domain.member.api.dto.RemoveMemberBody
import com.imedicine.imedicine.security.AuthUser
import com.imedicine.imedicine.security.LeaderAuthorize
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@Tag(name = "Member")
@RestController
@RequestMapping("/api/v1/members")
class MemberController(private val memberFacade: MemberFacade) {
    @Operation(summary = "팀원을 팀에 추가")
    @PostMapping
    @LeaderAuthorize
    fun addMemberToTeam(
        @AuthenticationPrincipal user: AuthUser,
        @RequestBody body: AddMemberBody
    ): ApiResponse<Unit> =
        ApiResponse.success(memberFacade.addMemberToTeam(body.teamId, body.userId))

    @Operation(summary = "팀원을 팀에 제외")
    @DeleteMapping
    @LeaderAuthorize
    fun removeMemberFromTeam(
        @AuthenticationPrincipal user: AuthUser,
        @RequestBody body: RemoveMemberBody
    ): ApiResponse<Unit> =
        ApiResponse.success(memberFacade.removeMemberFromTeam(body.teamId, body.userId))
}