package com.imedicine.imedicine.common.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

data class PaginationQuery(
    @Schema(description = "page", example = "1", defaultValue = "1")
    @field:Min(value = 1, message = "페이지는 무조건 1 이상이어야 합니다.")
    val page: Int,

    @Schema(description = "팀 이름", example = "20", defaultValue = "20")
    @field:Min(value = 1, message = "페이지 사이즈는 무조건 1 이상이어야 합니다.")
    val size: Int,
)
