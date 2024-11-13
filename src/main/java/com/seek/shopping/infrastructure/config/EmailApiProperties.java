package com.seek.shopping.infrastructure.config;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "api.email")
public record EmailApiProperties(
    @NotEmpty String url,
    @NotEmpty String apiKey
) {

}