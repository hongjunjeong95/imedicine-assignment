package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.common.dto.PaginationQuery
import com.imedicine.imedicine.domain.team.api.dto.CreateTeamBodyDto
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.user.service.UserService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamFacade(
    private val teamService: TeamService,
    private val userService: UserService,
) {
    fun createTeam(userId: Long, body: CreateTeamBodyDto): Unit {
        val leader = userService.me(userId)
        teamService.save(
            with(body) {
                Team(
                    name = name,
                    leader = leader
                )
            }
        )
    }

    fun findTeams(query:PaginationQuery):Page<Team> =
        teamService.findMany(query.page,query.size)

    fun findTeam(teamId: Long): Team =
        teamService.findByIdOrNull(teamId)
}