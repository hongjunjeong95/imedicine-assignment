package com.imedicine.imedicine.facade

import com.imedicine.imedicine.domain.member.api.MemberFacade
import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.member.service.MemberService
import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.stub.teamStub
import com.imedicine.imedicine.stub.userStub
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException

@ExtendWith(SpringExtension::class)
class MemberFacadeTest: BehaviorSpec() {
    private val mockUser = userStub()
    private val mockTeam = teamStub()

    init {
        val teamService = mockk<TeamService>()
        val userService = mockk<UserService>()
        val memberService = mockk<MemberService>()

        val facade = MemberFacade(teamService, userService, memberService)

        afterEach {
            clearAllMocks()
        }

        Given("teamId with no team"){
            val teamId:Long = 12
            val userId:Long = 1
            every { teamService.findByIdOrNull(teamId) }.throws(ResponseStatusException(HttpStatus.NOT_FOUND))
            When("you add member to team") {
                Then("throws an ResponseStatusException"){
                    shouldThrow<ResponseStatusException> {
                        facade.addMemberToTeam(teamId,userId)
                    }
                }
            }
        }

        Given("userId with no user to add to team"){
            val teamId:Long = 1
            val userId:Long = 12
            every { teamService.findByIdOrNull(teamId) } returns mockTeam
            every { userService.findByIdOrNull(userId) }.throws(ResponseStatusException(HttpStatus.NOT_FOUND))
            When("you add member to team") {
                Then("throws an ResponseStatusException"){
                    shouldThrow<ResponseStatusException> {
                        facade.addMemberToTeam(teamId,userId)
                    }
                }
            }
        }

        Given("team and user Ids"){
            val teamId:Long = 1
            val userId:Long = 12
            every { teamService.findByIdOrNull(teamId) } returns mockTeam
            every { userService.findByIdOrNull(userId) } returns mockUser
            every { memberService.create(Member(mockTeam,mockUser)) }.throws(DataIntegrityViolationException("이미 팀원(${userId})이 팀(${teamId})에 추가되었습니다."))
            When("you find yourself(me)") {
                Then("throws an DataIntegrityViolationException"){
                    shouldThrow<DataIntegrityViolationException> {
                        facade.addMemberToTeam(teamId,userId)
                    }
                }
            }
        }
    }
}