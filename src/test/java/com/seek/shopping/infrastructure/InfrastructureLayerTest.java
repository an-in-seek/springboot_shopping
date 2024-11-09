package com.seek.shopping.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.seek.shopping.IntegrationTest;
import com.seek.shopping.infrastructure.persistence.entity.MemberEntity;
import com.seek.shopping.infrastructure.persistence.repository.MemberJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("인프라스트럭쳐 계층 테스트")
public class InfrastructureLayerTest extends IntegrationTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("Member 생성 테스트")
    void testCreateMember() {
        // given
        final String name = "seek";
        final String email = "seek1@naver.com";
        final MemberEntity memberEntity = MemberEntity.builder()
            .name(name)
            .email(email)
            .build();

        // when
        MemberEntity createdMember = memberJpaRepository.save(memberEntity);

        // then
        assertThat(createdMember.getId()).isNotNull();
        assertThat(createdMember.getName()).isNotNull().isEqualTo(name);
        assertThat(createdMember.getEmail()).isNotNull().isEqualTo(email);
    }

    @Test
    @DisplayName("Member 조회 테스트")
    void testFindMember() {
        // given
        final String name = "seek";
        final String email = "seek1@naver.com";
        final MemberEntity memberEntity = MemberEntity.builder()
            .name(name)
            .email(email)
            .build();
        final Long createdMemberId = memberJpaRepository.saveAndFlush(memberEntity).getId();

        // when
        Optional<MemberEntity> member = memberJpaRepository.findById(createdMemberId);

        // then
        assertThat(member).isPresent();
        assertThat(member.get().getId()).isNotNull().isEqualTo(createdMemberId);
        assertThat(member.get().getName()).isNotNull().isEqualTo(name);
        assertThat(member.get().getEmail()).isNotNull().isEqualTo(email);
    }
}
