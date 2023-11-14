package com.imedicine.imedicine.domain.member.persistent

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.fasterxml.jackson.annotation.JsonIgnore
import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.user.persistent.User
import jakarta.persistence.*
import org.hibernate.annotations.Where

@Entity
@Table(name = "member")
@Where(clause = "deleted_at is null")
class Member(
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "team_id")
    val team:Team,

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
): BaseEntity(){
    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(Member::id)
        private val toStringProperties = arrayOf(
            Member::id,
        )
    }
}