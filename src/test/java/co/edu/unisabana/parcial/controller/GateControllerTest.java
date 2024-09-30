package co.edu.unisabana.parcial.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GateControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void checkin() {
        String url = "http://localhost:" + port + "/api/gate/checkin";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void checkout() {
        String url = "http://localhost:" + port + "/api/gate/checkout";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
