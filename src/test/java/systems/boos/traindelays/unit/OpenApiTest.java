package systems.boos.traindelays.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OpenApiTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertTrue(true, "Spring application context must load.");
    }

    @Test
    void getOpenApiJson__ReturnsValidOpenApiDescription() {
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/v3/api-docs", String.class);
        assertThat(response, startsWith("{\"openapi\":\""));
        assertThat(response, endsWith("}"));
    }
}
