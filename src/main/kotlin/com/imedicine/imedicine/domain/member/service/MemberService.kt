package com.imedicine.imedicine.domain.member.service

import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.member.persistent.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun create(param: Member) =
        memberRepository.save(param)
}