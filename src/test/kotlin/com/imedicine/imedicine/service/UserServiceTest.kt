package com.imedicine.imedicine.service


import com.imedicine.imedicine.common.persistent.findByIdOrThrow
import com.imedicine.imedicine.domain.user.persistent.User
import com.imedicine.imedicine.domain.user.persistent.UserRepository
import com.imedicine.imedicine.domain.user.service.UserService
import com.imedicine.imedicine.stub.userStub
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class UserServiceTest: BehaviorSpec() {
    private val mockUser = userStub()

    init {
        val userRepository = mockk<UserRepository>()
        val service = UserService(userRepository)

        afterEach {
            clearAllMocks()
        }

        Given("id with no user"){
            val id:Long = 1
            every { userRepository.findByIdOrThrow(id, "존재하지 않는 회원입니다.") } returns null
            When("you find yourself(me)") {
                Then("throws an NoSuchElementException"){
                    shouldThrow<NoSuchElementException> {
                        service.me(id)
                    }
                }
            }
        }

        Given("id with user"){
            val id:Long = 1
            every { userRepository.findByIdOrThrow(id, "존재하지 않는 회원입니다.") } returns mockUser
            When("you find yourself(me)") {
                val result: User = service.me(id)

                Then("it returns SignInReturn"){
                    result shouldBe mockUser
                }
            }
        }
    }
}