package com.imedicine.imedicine.security

import com.imedicine.imedicine.domain.team.service.TeamService
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class CustomPermissionEvaluator(private val teamService: TeamService) : PermissionEvaluator {

    override fun hasPermission(authentication: Authentication, targetDomainObject: Any, permission: Any): Boolean {
        return false
    }

    override fun hasPermission(
        authentication: Authentication,
        targetId: Serializable,
        targetType: String,
        permission: Any
    ): Boolean {
        val team = teamService.findByIdOrNull(targetId as Long)
        return team.leader.id === (authentication.principal as AuthUser).id
    }
}
