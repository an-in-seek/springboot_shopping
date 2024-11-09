package com.seek.shopping.domain;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseDomainModel {

    private Long id;
    private String name;
    private String email;
    private Address address;
    private List<Order> orders = List.of();

    protected Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Member create(String username, String email) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        return new Member(null, username, email);
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public Member updateEmail(String newEmail) {
        return new Member(this.id, this.name, newEmail);
    }
}
