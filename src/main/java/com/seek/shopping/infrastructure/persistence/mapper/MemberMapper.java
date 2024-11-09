package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Member;
import com.seek.shopping.infrastructure.persistence.entity.MemberEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    // 엔티티를 도메인 모델로 변환
    public static Member toDomainModel(MemberEntity entity) {
        return Member.builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .build();
    }

    // 도메인 모델을 엔티티로 변환
    public static MemberEntity toEntity(Member domainModel) {
        return MemberEntity.builder()
            .id(domainModel.getId())
            .name(domainModel.getName())
            .email(domainModel.getEmail())
            .build();
    }
}
