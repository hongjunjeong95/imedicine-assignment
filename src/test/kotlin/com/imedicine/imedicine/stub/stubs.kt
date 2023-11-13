package com.imedicine.imedicine.stub

import com.imedicine.imedicine.common.utils.BCryptUtils
import com.imedicine.imedicine.domain.team.persistent.Team
import com.imedicine.imedicine.domain.user.persistent.User


fun userStub():User {
    val user: User  by lazy {
        User (
            email = "hongjun@gmail.com",
            username = "hongjun@gmail.com",
            password = BCryptUtils.encrypt("12345678"),
        )
    }
    return user
}

fun teamStub(): Team {
    val user: Team  by lazy {
        Team (
            name = "연구 1팀",
            leader = userStub(),
        )
    }
    return user
}