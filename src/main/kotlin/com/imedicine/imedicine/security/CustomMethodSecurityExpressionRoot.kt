package com.imedicine.imedicine.security

import org.springframework.security.access.expression.SecurityExpressionRoot
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication

class CustomMethodSecurityExpressionRoot(authentication: Authentication) :
    SecurityExpressionRoot(authentication), MethodSecurityExpressionOperations {
    private val filterObject: Any? = null
    private val returnObject: Any? = null


    override fun setFilterObject(filterObject: Any?) {
        TODO("Not yet implemented")
    }

    override fun getFilterObject(): Any? = filterObject
    override fun setReturnObject(returnObject: Any?) {
        TODO("Not yet implemented")
    }

    override fun getReturnObject(): Any? = returnObject

    override fun getThis(): Any {
        return this
    }
}
