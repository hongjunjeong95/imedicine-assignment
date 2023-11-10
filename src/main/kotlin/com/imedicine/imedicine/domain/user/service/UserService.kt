package com.imedicine.imedicine.domain.user.service

import com.imedicine.imedicine.common.persistent.findByIdOrThrow
import com.imedicine.imedicine.domain.user.persistent.User
import com.imedicine.imedicine.domain.user.persistent.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun me(id: Long): User = userRepository.findByIdOrThrow(id, "존재하지 않는 회원입니다.")
}