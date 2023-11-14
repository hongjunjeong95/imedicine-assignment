package com.imedicine.imedicine.domain.teamData.persistent

import com.imedicine.imedicine.common.persistent.BaseRepository


interface TeamDataRepository: BaseRepository<TeamData, Long>{
    fun findByTeamId(teamId: Long): List<TeamData>
    fun findByTeamIdAndUserId(teamId: Long, userId: Long): List<TeamData>
}