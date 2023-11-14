package com.imedicine.imedicine.common.service

import com.imedicine.imedicine.common.persistent.BaseEntity
import com.imedicine.imedicine.common.persistent.BaseRepository
import com.imedicine.imedicine.domain.teamData.persistent.TeamData
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class CommonService<T: BaseEntity>(
    private val repository: BaseRepository<T, Long>
) {
    open fun save(param: T) =
        repository.save(param)

    open fun findByIdOrNull(id: Long): T =
         repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    open fun findAll():List<T> = repository.findAll()

    open fun softDeleteById(id: Long) =
        repository.softDeleteById(id)
}