package com.imedicine.imedicine.domain.user.api

import com.imedicine.imedicine.common.dto.ApiResponse
import com.imedicine.imedicine.security.AuthUser
import com.imedicine.imedicine.security.UserAuthorize
import com.imedicine.imedicine.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "User")
@UserAuthorize
@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {
    @Operation(summary = "회원 정보 조회")
    @GetMapping("/me")
    fun me(@AuthenticationPrincipal user: AuthUser) =
        ApiResponse.success(userService.me(user.id))
}