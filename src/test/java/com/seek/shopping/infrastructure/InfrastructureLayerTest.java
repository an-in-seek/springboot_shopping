package com.seek.shopping.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.seek.jpa.study.IntegrationTest;
import com.seek.jpa.study.infrastructure.persistence.MemberEntity;
import com.seek.jpa.study.infrastructure.persistence.MemberJpaRepository;
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
        final String username = "seek";
        final String email = "seek1@naver.com";
        final MemberEntity memberEntity = MemberEntity.builder()
            .username(username)
            .email(email)
            .build();

        // when
        MemberEntity createdMember = memberJpaRepository.save(memberEntity);

        // then
        assertThat(createdMember.getId()).isNotNull();
        assertThat(createdMember.getUsername()).isNotNull().isEqualTo(username);
        assertThat(createdMember.getEmail()).isNotNull().isEqualTo(email);
    }

    @Test
    @DisplayName("Member 조회 테스트")
    void testFindMember() {
        // given
        final String username = "seek";
        final String email = "seek1@naver.com";
        final MemberEntity memberEntity = MemberEntity.builder()
            .username(username)
            .email(email)
            .build();
        final Long createdMemberId = memberJpaRepository.saveAndFlush(memberEntity).getId();

        // when
        Optional<MemberEntity> member = memberJpaRepository.findById(createdMemberId);

        // then
        assertThat(member).isPresent();
        assertThat(member.get().getId()).isNotNull().isEqualTo(createdMemberId);
        assertThat(member.get().getUsername()).isNotNull().isEqualTo(username);
        assertThat(member.get().getEmail()).isNotNull().isEqualTo(email);
    }
}
