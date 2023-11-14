package com.imedicine.imedicine.domain.team.persistent

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import com.imedicine.imedicine.domain.user.persistent.User
import jakarta.persistence.*


@Entity
@Table(name = "team")
class Team(
    @Column(name = "\"name\"")
    var name: String,

    @ManyToOne
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
}