package com.imedicine.imedicine.common.persistent

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

inline fun <reified T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, message: String = "해당 ID의 데이터가 존재하지 않습니다."): T = findByIdOrNull(id) ?: throw NoSuchElementException(message)