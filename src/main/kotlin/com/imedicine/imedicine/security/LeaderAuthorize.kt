package com.imedicine.imedicine.security

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasPermission(#body.teamId,'team','read')")
annotation class LeaderAuthorize