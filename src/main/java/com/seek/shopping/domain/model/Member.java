package com.seek.shopping.domain.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseDomainModel {

    private Long id;
    private String name;
    private String email;
    private Address address;

    @Default
    private List<Order> orders = new ArrayList<>();

    protected Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Member create(String username, String email) {
        validateUsername(username);
        validateEmail(email);
        return new Member(null, username, email);
    }

    public Member updateEmail(String newEmail) {
        validateEmail(newEmail);
        return new Member(this.id, this.name, newEmail);
    }

    private static void validateUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username은 필수 값입니다.");
        }
    }

    private static void validateEmail(String email) {
        if (!StringUtils.hasText(email) || !email.contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 email 형식입니다.");
        }
    }
}
