package com.imedicine.imedicine.domain.team.api

import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.member.service.MemberService
import com.imedicine.imedicine.domain.team.api.dto.AddMemberToTeamBody
import com.imedicine.imedicine.domain.team.api.dto.CreateTeamBody
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.security.AuthUser
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamFacade(
    private val teamService: TeamService,
    private val userService: UserService,
    private val memberService:MemberService
) {
    fun create(user:AuthUser, body: CreateTeamBody) {
        val leader = userService.me(user.id)
        teamService.create(
            with(body){
                Team(
                    name = name,
                    leader = leader
                )
            }
        )
    }

    @Transactional
    fun addMemberToTeam(id:Long, body: AddMemberToTeamBody) {
        val team = teamService.findByIdOrNull(id)
        val user = userService.findByIdOrNull(id)

        try {
            memberService.create(
                Member(
                    team,
                    user
                )
            )
        } catch (e: DataIntegrityViolationException){
            throw DataIntegrityViolationException("이미 팀(${id})에 팀원(${user.id})이 추가되었습니다.")
        }
    }
}