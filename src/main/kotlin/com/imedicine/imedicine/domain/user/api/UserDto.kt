package com.imedicine.imedicine.domain.user.api

import com.imedicine.imedicine.domain.user.persistent.User
import com.imedicine.imedicine.domain.user.persistent.UserRole
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    var email: String,
    var username: String,
    val role: UserRole = UserRole.USER,
){
    companion object {
        fun of(teamData: User): UserResponse {
            checkNotNull(teamData.id)
            return with(teamData) {
                UserResponse(
                    id,
                    createdAt,
                    updatedAt,
                    email,
                    username,
                    role
                )
            }
        } }
}