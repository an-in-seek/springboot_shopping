package com.seek.shopping.domain.service;

import com.seek.shopping.domain.model.Member;
import com.seek.shopping.infrastructure.persistence.entity.MemberEntity;
import com.seek.shopping.infrastructure.persistence.mapper.MemberMapper;
import com.seek.shopping.infrastructure.persistence.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberJpaRepository memberJpaRepository;

    public Member createMember(String username, String email) {
        // Member 정보 생성 비지니스 로직 수행
        Member member = Member.create(username, email);

        // 비즈니스 로직 처리 후 MemberEntity로 변환하여 저장
        MemberEntity createdMemberEntity = memberJpaRepository.save(MemberMapper.toEntity(member));

        // Entity를 Domain Model로 변환
        return MemberMapper.toDomainModel(createdMemberEntity);
    }

    public Member updateMemberEmail(Long memberId, String newEmail) {
        // memberId로 Member 정보 조회
        Member member = memberJpaRepository.findById(memberId)
            .map(MemberMapper::toDomainModel)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, memberId + "가 존재하지 않습니다."));

        // 도메인 모델을 통해 Email 변경 비즈니스 로직 수행
        Member updatedUser = member.updateEmail(newEmail);

        // 비즈니스 로직 처리 후 MemberEntity로 변환하여 저장
        MemberEntity updatedMemberEntity = memberJpaRepository.save(MemberMapper.toEntity(updatedUser));

        // Entity를 Domain Model로 변환
        return MemberMapper.toDomainModel(updatedMemberEntity);
    }
}
