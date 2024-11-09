package com.seek.shopping.presentation.controller;

import com.seek.shopping.application.MemberDto;
import com.seek.shopping.application.MemberFacade;
import com.seek.shopping.presentation.request.MemberCreateRequest;
import com.seek.shopping.presentation.request.MemberEmailModifyRequest;
import com.seek.shopping.presentation.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {

    private final MemberFacade memberFacade;

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberCreateRequest request) {
        MemberDto createdMember = memberFacade.createMember(request.username(), request.email());
        MemberResponse memberResponse = MemberResponse.from(createdMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberEmail(@PathVariable Long memberId, @RequestBody MemberEmailModifyRequest request) {
        MemberDto updateMember = memberFacade.updateMemberEmail(memberId, request.email());
        MemberResponse memberResponse = MemberResponse.from(updateMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
    }

}