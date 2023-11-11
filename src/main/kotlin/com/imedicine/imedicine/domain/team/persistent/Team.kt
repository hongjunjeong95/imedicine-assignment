package com.imedicine.imedicine.domain.team.persistent

import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.user.persistent.User
import jakarta.persistence.*


@Entity
@Table(name = "team")
data class Team(
    @Column(name = "\"name\"")
    private var name: String,

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private val leader: User,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    var members:  MutableList<Member> = mutableListOf()
): BaseEntity()