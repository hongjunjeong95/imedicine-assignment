package com.imedicine.imedicine.domain.member.service

import com.imedicine.imedicine.common.service.CommonService
import com.imedicine.imedicine.domain.member.persistent.Member
import com.imedicine.imedicine.domain.member.persistent.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
):CommonService<Member>(memberRepository) {
    fun delete(teamId: Long, userId: Long) =
        memberRepository.deleteByTeamIdAndUserId(teamId, userId)
}