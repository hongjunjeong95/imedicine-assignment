package com.imedicine.imedicine.domain.user.persistent

import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.member.persistent.Memeber
import com.imedicine.imedicine.domain.team.persistent.Team
import jakarta.persistence.*


@Entity
@Table(name = "user")
data class User(
    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Column
    var username: String,

    @Column(name = "current_hashed_refresh_token", nullable = true)
    var currentHashedRefreshToken: String? = null,

    @Enumerated(EnumType.STRING)
    val role: UserRole = UserRole.USER,

    @OneToMany
    @JoinColumn(name = "leader_id")
    private val team: MutableList<Team>? = null,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var members:  MutableList<Memeber> = mutableListOf()
): BaseEntity()

enum class UserRole {
    USER, ADMIN, ANONYMOUS
}
