package com.imedicine.imedicine.domain.teamData.service

import com.imedicine.imedicine.common.service.CommonService
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import com.imedicine.imedicine.domain.teamData.persistent.TeamDataRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamDataService(
    private val teamDataRepository: TeamDataRepository
): CommonService<TeamData>(teamDataRepository) {
    fun findByTeamId(teamId: Long): List<TeamData> =
        teamDataRepository.findByTeamId(teamId)

    fun findByTeamIdAndUserId(teamId: Long, userId: Long): List<TeamData> =
        teamDataRepository.findByTeamIdAndUserId(teamId, userId)
}
