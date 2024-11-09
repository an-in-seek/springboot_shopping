package com.seek.shopping.presentation.request;

public record MemberEmailModifyRequest (
    String email
){
    public static MemberEmailModifyRequest from(String email){
        return new MemberEmailModifyRequest(email);
    }
}
