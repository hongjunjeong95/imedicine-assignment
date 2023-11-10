package com.imedicine.imedicine.domain.member.persistent

import com.fasterxml.jackson.annotation.JsonIgnore
import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.user.persistent.User
import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Memeber(
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "team_id")
    val team:Team,

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
): BaseEntity()