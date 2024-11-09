package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Member;
import com.seek.shopping.infrastructure.persistence.entity.MemberEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static Member toDomainModel(MemberEntity entity) {
        return Member.builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .address(AddressMapper.toDomainModel(entity.getAddress()))
            .build();
    }

    public static MemberEntity toEntity(Member domainModel) {
        return MemberEntity.builder()
            .id(domainModel.getId())
            .name(domainModel.getName())
            .email(domainModel.getEmail())
            .address(AddressMapper.toEntity(domainModel.getAddress()))
            .build();
    }
}
