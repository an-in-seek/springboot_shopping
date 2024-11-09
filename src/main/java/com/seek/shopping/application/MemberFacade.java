package com.seek.shopping.application;

import com.seek.shopping.domain.Member;
import com.seek.shopping.domain.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberCommandService memberCommandService;

    public MemberDto createMember(String username, String email) {
        Member member = memberCommandService.createMember(username, email);
        return MemberDto.from(member);
    }

    public MemberDto updateMemberEmail(Long memberId, String newEmail) {
        Member member = memberCommandService.updateMemberEmail(memberId, newEmail);
        return MemberDto.from(member);
    }

}
