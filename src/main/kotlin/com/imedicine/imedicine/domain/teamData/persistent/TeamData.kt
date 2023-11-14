package com.imedicine.imedicine.domain.teamData.persistent

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.fasterxml.jackson.annotation.JsonIgnore
import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.user.persistent.User
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Table(name = "team_data")
@Where(clause = "deleted_at is null")
class TeamData(
    @Column(name = "memo")
    var memo: String,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    val team:Team,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
): BaseEntity() {
    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(TeamData::id)
        private val toStringProperties = arrayOf(
            TeamData::id,
        )
    }

    fun update(memo: String,){
        this.memo = memo
        this.updatedAt = LocalDateTime.now()
    }
}