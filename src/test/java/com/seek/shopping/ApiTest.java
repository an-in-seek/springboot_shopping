package com.seek.shopping;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ApiTest {

    @LocalServerPort
    private int port;

    protected String generateUrl(String apiPath) {
        return "http://localhost:" + port + apiPath;
    }
}
