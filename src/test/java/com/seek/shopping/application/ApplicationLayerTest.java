package com.seek.shopping.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.seek.shopping.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("애플리케이션 계층 테스트")
public class ApplicationLayerTest extends IntegrationTest {

    @Autowired
    private MemberFacade memberFacade;

    @Test
    @DisplayName("Member 생성 테스트")
    void testCreateMember() {
        // given
        final String username = "seek";
        final String email = "seek1@naver.com";

        // when
        MemberDto createdMember = memberFacade.createMember(username, email);

        // then
        assertThat(createdMember.id()).isNotNull();
        assertThat(createdMember.username()).isNotNull().isEqualTo(username);
        assertThat(createdMember.email()).isNotNull().isEqualTo(email);
    }

    @Test
    @DisplayName("Member 이메일 수정 테스트")
    void testUpdateMemberEmail() {
        // given
        final String oldEmail = "seek111@naver.com";
        final String newEmail = "seek222@naver.com";
        final MemberDto createdMember = memberFacade.createMember("seek", oldEmail);
        final Long createdMemberId = createdMember.id();

        // when
        MemberDto updatedUser = memberFacade.updateMemberEmail(createdMemberId, newEmail);

        // then
        assertThat(updatedUser.id()).isNotNull().isEqualTo(createdMemberId);
        assertThat(updatedUser.email()).isNotNull().isEqualTo(newEmail);
    }
}
