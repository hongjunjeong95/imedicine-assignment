package com.imedicine.imedicine.domain.teamData.api

import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.teamData.api.dto.CreateTeamDataBodyDto
import com.imedicine.imedicine.domain.teamData.api.dto.UpdateTeamDataBodyDto
import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import com.imedicine.imedicine.domain.teamData.service.TeamDataService
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.security.AuthUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamDataFacade(
    private val teamDataService: TeamDataService,
    private val teamService: TeamService,
    private val userService: UserService
) {
    fun createTeamData(userId:Long, teamId:Long, body: CreateTeamDataBodyDto) {
        val team = teamService.findByIdOrNull(body.teamId)
        val user = userService.findByIdOrNull(userId)
        teamDataService.save(
            TeamData(
                memo = body.memo,
                team = team,
                user = user
            )
        )
    }

    fun findTeamDataList(user: AuthUser, teamId: Long):List<TeamData> {
        val userId = user.id
        val team = teamService.findByIdOrNull(teamId)

        return if(team.leader.id == userId){
            teamDataService.findByTeamId(teamId)
        } else {
            teamDataService.findByTeamIdAndUserId(teamId, userId)
        }
    }

    fun findTeamData(teamDataId: Long): TeamData {
        return teamDataService.findByIdOrNull(teamDataId)
    }

    fun updateTeamData(teamDataId: Long, body: UpdateTeamDataBodyDto?) {
        checkNotNull(body) { "UpdateTeamDataBodyDto is null" }
        return teamDataService.findByIdOrNull(teamDataId).let {
            it.update(body.memo)
            teamDataService.save(it)
        } }

    fun deleteTeamData(id: Long) = teamDataService.softDeleteById(id)
}