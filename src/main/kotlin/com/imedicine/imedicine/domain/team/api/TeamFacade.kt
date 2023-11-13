package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.domain.team.api.dto.TeamDto
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.security.AuthUser
import org.springframework.stereotype.Service

@Service
class TeamFacade(
    private val teamService: TeamService,
    private val userService: UserService,
) {
    fun create(user: AuthUser, body: TeamDto) {
        val leader = userService.me(user.id)
        teamService.create(
            with(body) {
                Team(
                    name = name,
                    leader = leader
                )
            }
        )
    }
}