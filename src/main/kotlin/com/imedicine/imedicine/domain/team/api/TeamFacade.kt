package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.domain.team.api.dto.CreateTeamDto
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.security.AuthUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamFacade(
    private val teamService: TeamService,
    private val userService: UserService,
) {
    fun create(user: AuthUser, body: CreateTeamDto) {
        val leader = userService.me(user.id)
        teamService.save(
            with(body) {
                Team(
                    name = name,
                    leader = leader
                )
            }
        )
    }
}