package co.edu.unisabana.parcial.controller;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.controller.dto.ResponseGate;
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
        // Aquí puedes configurar cualquier dato necesario antes de cada prueba
    }

    @Test
    void checkin() {
        String url = "http://localhost:" + port + "/checkpoint/checkin";

        // Crea un objeto CheckpointDTO para el check-in
        CheckpointDTO checkpoint = new CheckpointDTO();
        // Establece los valores necesarios en checkpoint según tu implementación
        // checkpoint.setSomeField(value); // Ajusta según tus campos

        ResponseEntity<ResponseGate> response = restTemplate.postForEntity(url, checkpoint, ResponseGate.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isTrue(); // Verifica si la respuesta es exitosa
    }

    @Test
    void checkout() {
        String url = "http://localhost:" + port + "/checkpoint/checkout";

        // Crea un objeto CheckpointDTO para el check-out
        CheckpointDTO checkpoint = new CheckpointDTO();
        // Establece los valores necesarios en checkpoint según tu implementación
        // checkpoint.setSomeField(value); // Ajusta según tus campos

        ResponseEntity<ResponseGate> response = restTemplate.postForEntity(url, checkpoint, ResponseGate.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isTrue(); // Verifica si la respuesta es exitosa
    }
}
