package com.imedicine.imedicine.domain.team.service

import com.imedicine.imedicine.common.service.CommonService
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.team.persistent.TeamRepository
import org.springframework.stereotype.Service

@Service
class TeamService(
    private val teamRepository: TeamRepository
): CommonService<Team>(teamRepository)