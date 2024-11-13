package com.seek.shopping.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.seek.shopping.IntegrationTest;
import com.seek.shopping.domain.model.Member;
import com.seek.shopping.domain.service.MemberCommandService;
import com.seek.shopping.domain.service.MemberQueryService;
import com.seek.shopping.infrastructure.persistence.repository.MemberJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Member 도메인 테스트")
class MemberDomainTest extends IntegrationTest {

    @Autowired
    private MemberCommandService memberCommandService;

    @Autowired
    private MemberQueryService memberQueryService;

    @AfterEach
    void cleanUp(@Autowired MemberJpaRepository memberJpaRepository) {
        memberJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Member 생성 테스트")
    void testCreateMember() {
        // given
        final String name = "seek";
        final String email = "seek1@naver.com";

        // when
        Member createdMember = memberCommandService.createMember(name, email);

        // then
        assertThat(createdMember.getId()).isNotNull();
        assertThat(createdMember.getName()).isNotNull().isEqualTo(name);
        assertThat(createdMember.getEmail()).isNotNull().isEqualTo(email);
    }

    @Test
    @DisplayName("Member 이메일 수정 테스트")
    void testUpdateMemberEmail() {
        // given
        final String oldEmail = "seek111@naver.com";
        final String newEmail = "seek222@naver.com";
        final Member createdMember = memberCommandService.createMember("seek", oldEmail);
        final Long createdMemberId = createdMember.getId();

        // when
        Member updatedMember = memberCommandService.updateMemberEmail(createdMemberId, newEmail);

        // then
        assertThat(updatedMember.getId()).isNotNull().isEqualTo(createdMemberId);
        assertThat(updatedMember.getEmail()).isNotNull().isEqualTo(newEmail);
    }

    @Test
    @DisplayName("Member ID 기준으로 Member 조회 테스트")
    void testGetMemberById() {
        // given
        final String name = "seek";
        final String email = "seek1@naver.com";
        final Long memberId = memberCommandService.createMember(name, email).getId();

        // when
        Optional<Member> member = memberQueryService.getMemberById(memberId);

        // then
        assertThat(member).isPresent();
        assertThat(member.get().getId()).isNotNull().isEqualTo(memberId);
        assertThat(member.get().getName()).isNotNull().isEqualTo(name);
        assertThat(member.get().getEmail()).isNotNull().isEqualTo(email);
    }

    @Test
    @DisplayName("Email 기준으로 Member 조회 테스트")
    void testGetMember() {
        // given
        final String name = "seek";
        final String email = "seek1@naver.com";
        final Long memberId = memberCommandService.createMember(name, email).getId();

        // when
        Optional<Member> member = memberQueryService.getMemberByEmail(email);

        // then
        assertThat(member).isPresent();
        assertThat(member.get().getId()).isNotNull().isEqualTo(memberId);
        assertThat(member.get().getName()).isNotNull().isEqualTo(name);
        assertThat(member.get().getEmail()).isNotNull().isEqualTo(email);
    }
}
