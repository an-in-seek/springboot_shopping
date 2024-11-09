package com.seek.shopping.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.seek.jpa.study.IntegrationTest;
import com.seek.shopping.domain.service.MemberCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("도메인 계층 테스트")
class DomainLayerTest extends IntegrationTest {

    @Autowired
    private MemberCommandService memberCommandService;

    @Test
    @DisplayName("Member 생성 테스트")
    void testCreateMember() {
        // given
        final String username = "seek";
        final String email = "seek1@naver.com";

        // when
        Member createdMember = memberCommandService.createMember(username, email);

        // then
        assertThat(createdMember.getId()).isNotNull();
        assertThat(createdMember.getName()).isNotNull().isEqualTo(username);
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
        Member updatedUser = memberCommandService.updateMemberEmail(createdMemberId, newEmail);

        // then
        assertThat(updatedUser.getId()).isNotNull().isEqualTo(createdMemberId);
        assertThat(updatedUser.getEmail()).isNotNull().isEqualTo(newEmail);
    }
}
