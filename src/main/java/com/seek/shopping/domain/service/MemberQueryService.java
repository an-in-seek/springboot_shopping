package com.seek.shopping.domain.service;

import com.seek.shopping.domain.Member;
import com.seek.shopping.infrastructure.persistence.mapper.MemberMapper;
import com.seek.shopping.infrastructure.persistence.repository.MemberJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberJpaRepository memberJpaRepository;

    public Optional<Member> getMemberById(Long memberId) {
        return memberJpaRepository.findById(memberId).map(MemberMapper::toDomainModel);
    }

    public Optional<Member> getMemberByEmail(String email) {
        return memberJpaRepository.findByEmail(email).map(MemberMapper::toDomainModel);
    }

}
