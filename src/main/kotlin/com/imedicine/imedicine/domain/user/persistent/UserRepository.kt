package com.imedicine.imedicine.domain.user.persistent

import com.imedicine.imedicine.common.persistent.BaseRepository

interface UserRepository: BaseRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findByUsername(username: String) : User?
}