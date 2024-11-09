package com.seek.shopping.infrastructure.persistence.mapper;

import com.seek.shopping.domain.Member;
import com.seek.shopping.infrastructure.persistence.entity.MemberEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static Member toDomainModel(MemberEntity entity) {
        if (entity == null) {
            return null;
        }
        return Member.builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .address(AddressMapper.toDomainModel(entity.getAddress()))
            .build();
    }

    public static MemberEntity toEntity(Member member) {
        if (member == null) {
            return null;
        }
        return MemberEntity.builder()
            .id(member.getId())
            .name(member.getName())
            .email(member.getEmail())
            .address(AddressMapper.toEntity(member.getAddress()))
            .build();
    }
}
