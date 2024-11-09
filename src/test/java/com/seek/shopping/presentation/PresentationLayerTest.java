package com.seek.shopping.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import com.seek.shopping.ApiTest;
import com.seek.shopping.presentation.request.MemberCreateRequest;
import com.seek.shopping.presentation.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@DisplayName("프리젠테이션 계층 테스트")
public class PresentationLayerTest extends ApiTest {

    private final RestClient restClient = RestClient.create();

    @Test
    @DisplayName("Member 생성 테스트")
    void testCreateMember() {
        // given
        final String username = "seek";
        final String email = "seek1@naver.com";
        final String url = "/api/v1/members";
        final MemberCreateRequest memberCreateRequest = MemberCreateRequest.of(username, email);

        // when
        ResponseEntity<MemberResponse> response = restClient.post()
            .uri(generateUrl(url))
            .contentType(MediaType.APPLICATION_JSON)
            .body(memberCreateRequest)
            .retrieve()
            .toEntity(MemberResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isNotNull();
        assertThat(response.getBody().username()).isNotNull().isEqualTo(username);
        assertThat(response.getBody().email()).isNotNull().isEqualTo(email);
    }
}
