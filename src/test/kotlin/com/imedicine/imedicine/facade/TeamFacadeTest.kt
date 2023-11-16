package com.imedicine.imedicine.facade

import com.imedicine.imedicine.common.dto.PaginationQuery
import com.imedicine.imedicine.domain.team.api.TeamFacade
import com.imedicine.imedicine.domain.team.api.dto.CreateTeamBodyDto
import com.imedicine.imedicine.domain.team.service.TeamService
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.stub.teamStub
import com.imedicine.imedicine.stub.userStub
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException

@ExtendWith(SpringExtension::class)
class TeamFacadeTest: BehaviorSpec() {
    private val mockUser = userStub()
    private val mockTeam = teamStub()

    init {
        val teamService = mockk<TeamService>()
        val userService = mockk<UserService>()
        val facade = TeamFacade(teamService, userService)

        afterEach {
            clearAllMocks()
        }

        Given("userId with no user"){
            val userId:Long = 1
            val body = CreateTeamBodyDto(
                name = "연구 1팀"
            )
            every { userService.me(userId) }.throws(ResponseStatusException(HttpStatus.NOT_FOUND))
            When("you create a team") {
                Then("throws an ResponseStatusException"){
                    shouldThrow<ResponseStatusException> {
                        facade.createTeam(userId, body)
                    }
                }
            }
        }

        Given("userId"){
            val userId:Long = 1
            val body = CreateTeamBodyDto(
                name = "연구 1팀"
            )
            every { userService.me(userId) } returns mockUser
            every { teamService.save(mockTeam) } returns mockTeam
            When("you create a team") {
                val result = facade.createTeam(userId, body)

                Then("it returns Unit"){
                    result shouldBe Unit
                }
            }
        }

        Given("pagination query"){
            val query = PaginationQuery(
                page = 1,
                size = 20
            )
            val (page, size) = query
            val pageRequest = PageRequest.of(page - 1, size, Sort.by("createdAt").descending().and(Sort.by("id")))

            val teams = listOf(mockTeam)
            val teamPageable = PageImpl(teams, pageRequest, teams.size.toLong())

            every { teamService.findMany(page, size) } returns teamPageable
            When("you find teams with pagination") {
                val result = facade.findTeams(query)

                Then("it returns teamPageable"){
                    result shouldBe teamPageable
                }
            }
        }

        Given("teamId with no team"){
            val teamId:Long = 1

            every { teamService.findByIdOrNull(teamId) }.throws(ResponseStatusException(HttpStatus.NOT_FOUND))
            When("you find a team") {
                Then("throws an ResponseStatusException"){
                    shouldThrow<ResponseStatusException> {
                        facade.findTeam(teamId)
                    }
                }
            }
        }

        Given("teamId"){
            val teamId:Long = 1

            every { teamService.findByIdOrNull(teamId) } returns mockTeam
            When("you find a team") {
                val result = facade.findTeam(teamId)
                Then("returns a team"){
                    result shouldBe mockTeam
                }
            }
        }
    }
}