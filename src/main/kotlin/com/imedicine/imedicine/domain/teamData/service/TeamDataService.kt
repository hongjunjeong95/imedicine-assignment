package com.imedicine.imedicine.domain.teamData.service

import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import com.imedicine.imedicine.domain.teamData.persistent.TeamDataRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamDataService(
    private val teamDataRepository: TeamDataRepository
) {
    fun create(param: TeamData) =
        teamDataRepository.save(param)

    fun findByTeamId(teamId: Long) =
        teamDataRepository.findByTeamId(teamId)

    fun findByTeamIdAndUserId(teamId: Long, userId: Long) =
        teamDataRepository.findByTeamIdAndUserId(teamId, userId)
}
