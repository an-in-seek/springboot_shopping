package com.seek.shopping.application;

import com.seek.shopping.domain.Member;
import lombok.Builder;

@Builder
public record MemberDto(
    Long id,
    String username,
    String email
) {

    public static MemberDto from(Member member) {
        return MemberDto.builder()
            .id(member.getId())
            .username(member.getName())
            .email(member.getEmail())
            .build();
    }
}
