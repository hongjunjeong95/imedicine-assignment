package com.imedicine.imedicine.domain.member.api

import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.member.service.MemberService
import com.imedicine.imedicine.domain.team.api.dto.TeamDto
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.security.AuthUser
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberFacade(
    private val teamService: TeamService,
    private val userService: UserService,
    private val memberService:MemberService
) {
    fun create(user:AuthUser, body: TeamDto) {
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
    fun addMemberToTeam(teamId:Long, userId: Long) {
        val team = teamService.findByIdOrNull(teamId)
        val user = userService.findByIdOrNull(userId)

        try {
            memberService.create(
                Member(
                    team,
                    user
                )
            )
        } catch (e: DataIntegrityViolationException){
            throw DataIntegrityViolationException("이미 팀원(${userId})이 팀(${teamId})에 추가되었습니다.")
        }
    }

    @Transactional
    fun removeMemberFromTeam(teamId:Long, userId: Long) {
        memberService.delete(teamId, userId)
    }
}