package com.imedicine.imedicine.security

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasPermission(#teamId,'leader','read') or hasPermission(#teamId,'member','read')")
annotation class LeaderAndMemberAuthorize