package com.imedicine.imedicine.domain.team.persistent

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.fasterxml.jackson.annotation.JsonIgnore
import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import com.imedicine.imedicine.domain.user.persistent.User
import jakarta.persistence.*
import org.hibernate.annotations.Where


@Entity
@Table(name = "team")
@Where(clause = "deleted_at is null")
class Team(
    @Column(name = "\"name\"")
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    val leader: User,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    var members:  MutableList<Member> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    var dataList:  MutableList<TeamData> = mutableListOf()
): BaseEntity(){
    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(Team::id)
        private val toStringProperties = arrayOf(
            Team::id,
        )
    }

    fun isUserInTeam(userId: Long): Boolean {
        return members.any { it.user.id == userId }
    }
}