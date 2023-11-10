package com.imedicine.imedicine.auth.service

import com.imedicine.imedicine.common.exception.PasswordNotMatchException
import com.imedicine.imedicine.common.exception.PasswordNotMatchedException
import com.imedicine.imedicine.common.exception.UserExistsException
import com.imedicine.imedicine.common.exception.UserNotFoundException
import com.imedicine.imedicine.common.utils.BCryptUtils
import com.imedicine.imedicine.domain.user.persistent.User
import com.imedicine.imedicine.domain.user.persistent.UserRepository
import com.imedicine.imedicine.security.JWTClaim
import com.imedicine.imedicine.security.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider
) {

    @Transactional
    fun signUp(param: SignUpParam) {
        with(param){
            userRepository.findByEmail(email)?.let{
                throw UserExistsException()
            }
            if(password != confirmPassword) {
                throw PasswordNotMatchException()
            }
            val user = User(
                email = email,
                password = BCryptUtils.encrypt(password),
                username = email,
                role = role
            )

            userRepository.save(user)
        }
    }

    @Transactional()
    fun signIn(param: SignInParam): SignInReturn {
        return with(userRepository.findByEmail(param.email) ?: throw UserNotFoundException()) {
            val verified = BCryptUtils.verify(param.password, password)
            if (!verified) {
                throw PasswordNotMatchedException()
            }

            val jwtClaim = JWTClaim(
                userId = id,
                email = email,
                username = username,
                role = role
            )

            val authToken = tokenProvider.createAuthToken(jwtClaim)
            val refreshToken = tokenProvider.createRefreshToken(jwtClaim)

            currentHashedRefreshToken = BCryptUtils.encrypt(refreshToken)
            userRepository.save(this)

            SignInReturn(
                authToken = authToken,
                refreshToken = refreshToken
            )
        }
    }
}