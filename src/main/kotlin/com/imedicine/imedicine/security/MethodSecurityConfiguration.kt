package com.imedicine.imedicine.security

import org.aopalliance.intercept.MethodInvocation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication

@Configuration
class MethodSecurityConfiguration(private val permissionEvaluator: CustomPermissionEvaluator) {
    @Bean
    fun methodSecurityExpressionHandler(): MethodSecurityExpressionHandler {
        val handler: DefaultMethodSecurityExpressionHandler = object : DefaultMethodSecurityExpressionHandler() {
            override fun createSecurityExpressionRoot(
                authentication: Authentication,
                invocation: MethodInvocation
            ): MethodSecurityExpressionOperations {
                val root = CustomMethodSecurityExpressionRoot(authentication)
                root.setPermissionEvaluator(permissionEvaluator)
                return root
            }
        }
        handler.setPermissionEvaluator(permissionEvaluator)
        return handler
    }
}
