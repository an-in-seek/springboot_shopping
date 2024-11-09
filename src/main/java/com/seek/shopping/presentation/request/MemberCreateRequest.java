package com.seek.shopping.presentation.request;

public record MemberCreateRequest(
    String username,
    String email
){

    public static MemberCreateRequest of(String username, String email){
        return new MemberCreateRequest(username, email);
    }
}
