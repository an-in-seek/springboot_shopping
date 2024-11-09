package com.seek.shopping.presentation.response;

import com.seek.shopping.application.MemberDto;

public record MemberResponse(
    Long id,
    String username,
    String email
) {

    public static MemberResponse from(MemberDto memberDto) {
        return new MemberResponse(memberDto.id(), memberDto.username(), memberDto.email());
    }
}