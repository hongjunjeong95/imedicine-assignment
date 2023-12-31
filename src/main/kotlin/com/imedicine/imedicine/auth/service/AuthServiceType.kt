package com.imedicine.imedicine.auth.service

import com.imedicine.imedicine.domain.user.persistent.UserRole


data class SignUpParam(
    val email: String,
    val password: String,
    val confirmPassword:String,
    val role: UserRole
)

data class SignInParam(
    val email: String,
    val password: String,
)

data class SignInReturn(
    val authToken: String,
    val refreshToken: String,
)