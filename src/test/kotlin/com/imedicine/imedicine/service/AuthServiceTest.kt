package com.imedicine.imedicine.service


import com.imedicine.imedicine.auth.service.AuthService
import com.imedicine.imedicine.auth.service.SignInParam
import com.imedicine.imedicine.auth.service.SignInReturn
import com.imedicine.imedicine.auth.service.SignUpParam
import com.imedicine.imedicine.common.exception.PasswordNotMatchException
import com.imedicine.imedicine.common.exception.PasswordNotMatchedException
import com.imedicine.imedicine.common.exception.UserExistsException
import com.imedicine.imedicine.common.exception.UserNotFoundException
import com.imedicine.imedicine.common.utils.BCryptUtils
import com.imedicine.imedicine.domain.user.persistent.User
import com.imedicine.imedicine.domain.user.persistent.UserRepository
import com.imedicine.imedicine.domain.user.persistent.UserRole
import com.imedicine.imedicine.security.JWTClaim
import com.imedicine.imedicine.security.TokenProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class AuthServiceTest: BehaviorSpec() {
    private val userStub: User by lazy {
        User(
            email = "hongjun@gmail.com",
            username = "hongjun@gmail.com",
            password = BCryptUtils.encrypt("12345678"),
        )
    }

    init {
        val userRepository = mockk<UserRepository>()
        val tokenProvider = mockk<TokenProvider>()

        val service = AuthService(userRepository, tokenProvider)

        afterEach {
            clearAllMocks()
        }

        Given("an account with the same name already exists on db"){
            val param = SignUpParam(
                email = "hongjun@gmail.com",
                password = "12345678",
                confirmPassword = "12345678",
                role = UserRole.USER
            )
            every { userRepository.findByEmail(param.email) } returns userStub
            When("you sign up") {
                Then("throws an UserExistsException exception"){
                    shouldThrow<UserExistsException> {
                        service.signUp(param)
                    }
                }
            }
        }

        Given("passwords which don't match"){
            val param = SignUpParam(
                email = "hongjun@gmail.com",
                password = "12345678",
                confirmPassword = "12345679",
                role = UserRole.USER
            )
            every { userRepository.findByEmail(param.email) } returns null
            When("you sign up") {
                Then("throws an PasswordNotMatchException"){
                    shouldThrow<PasswordNotMatchException> {
                        service.signUp(param)
                    }
                }
            }
        }

        Given("the email doesn't exist on user table on db"){
            val param = SignInParam(
                email = "hongjun@gmail.com",
                password = "12345678",
            )
            every { userRepository.findByEmail(param.email) } returns null
            When("you sign in") {
                Then("throws an UserNotFoundException"){
                    shouldThrow<UserNotFoundException> {
                        service.signIn(param)
                    }
                }
            }
        }

        Given("the password is wrong"){
            val param = SignInParam(
                email = "hongjun@gmail.com",
                password = "wrong password",
            )
            every { userRepository.findByEmail(param.email) } returns userStub
            When("you sign in") {
                Then("throws an PasswordNotMatchedException"){
                    shouldThrow<PasswordNotMatchedException> {
                        service.signIn(param)
                    }
                }
            }
        }

        Given("good param"){
            val param = SignInParam(
                email = "hongjun@gmail.com",
                password = "12345678",
            )
            val jwtClaim = JWTClaim(
                userId = userStub.id,
                email =  userStub.email,
                username =  userStub.username,
                role = userStub.role
            )

            every { userRepository.findByEmail(param.email) } returns userStub
            every { userRepository.save(userStub) } returns userStub
            every { tokenProvider.createAuthToken(jwtClaim) } returns "authTokenValue"
            every { tokenProvider.createRefreshToken(jwtClaim) } returns "refreshTokenValue"
            When("you sign in") {
                val result: SignInReturn = service.signIn(param)

                Then("it returns SignInReturn"){
                    result shouldBe SignInReturn(
                        authToken = tokenProvider.createAuthToken(jwtClaim),
                        refreshToken = tokenProvider.createRefreshToken(jwtClaim)
                    )
                }
            }
        }
    }
}