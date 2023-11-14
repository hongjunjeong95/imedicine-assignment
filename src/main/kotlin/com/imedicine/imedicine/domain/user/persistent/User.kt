package com.imedicine.imedicine.domain.user.persistent

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import com.imedicine.imedicine.domain.team.persistent.Team
import jakarta.persistence.*


@Entity
@Table(name = "user")
class User(
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
    var members:  MutableList<Member> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var dataList:  MutableList<TeamData> = mutableListOf()
): BaseEntity() {
    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(User::id)
        private val toStringProperties = arrayOf(
            User::id,
        )
    }
}

enum class UserRole {
    USER, ADMIN, ANONYMOUS
}
