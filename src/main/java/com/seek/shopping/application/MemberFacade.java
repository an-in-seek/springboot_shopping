package com.seek.shopping.application;

import com.seek.shopping.domain.Member;
import com.seek.shopping.domain.service.MemberCommandService;
import com.seek.shopping.domain.service.MemberQueryService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    public MemberDto createMember(String username, String email) {
        Member member = memberCommandService.createMember(username, email);
        return MemberDto.from(member);
    }

    public MemberDto updateMemberEmail(Long memberId, String newEmail) {
        Member member = memberCommandService.updateMemberEmail(memberId, newEmail);
        return MemberDto.from(member);
    }

    public Optional<MemberDto> getMember(Long memberId) {
        return memberQueryService.getMemberById(memberId).map(MemberDto::from);
    }

    public Optional<MemberDto> getMember(String email) {
        return memberQueryService.getMemberByEmail(email).map(MemberDto::from);
    }

}
