package co.edu.unisabana.parcial.ServicesTest;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import co.edu.unisabana.parcial.service.port.CheckpointPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestCheckpointService {

    @InjectMocks
    CheckpointService checkpointService;
    @Mock
    CheckpointPort checkpointPort;

    @Test
    void darUnDiaInvalido(){
        CheckpointDTO dto = new CheckpointDTO("Facility1", "Driver1", 15);

        checkpointService.checkin(dto);

        verify(checkpointPort, times(1)).saveCheckin(Mockito.any(Checkin.class));
    }

    @Test
    void pruebaDarUnDiaValido(){
        CheckpointDTO dto = new CheckpointDTO("Facility1", "Driver1", 15);
        Checkin existingCheckin = new Checkin("Facility1", "Driver1", 14);

        when(checkpointPort.findLastCheckin("Driver1", "Facility1")).thenReturn(existingCheckin);

        checkpointService.checkout(dto);
        verify(checkpointPort, times(1)).saveCheckout(Mockito.any(Checkout.class));
    }

    @Test
    void  pruebaSinPrevioCheckin(){
        CheckpointDTO dto = new CheckpointDTO("Facility1", "Driver1", 15);

        when(checkpointPort.findLastCheckin("Driver1", "Facility1")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> checkpointService.checkout(dto));
        verify(checkpointPort, never()).saveCheckout(Mockito.any(Checkout.class));
    }

    @Test
    void pruebaCheckoutConFechaIncorrcta(){
        CheckpointDTO dto = new CheckpointDTO("Facility1", "Driver1", 31);
        Checkin existingCheckin = new Checkin("Facility1", "Driver1", 14);

        when(checkpointPort.findLastCheckin("Driver1", "Facility1")).thenReturn(existingCheckin);

        assertThrows(IllegalArgumentException.class, () -> checkpointService.checkout(dto));
        verify(checkpointPort, never()).saveCheckout(Mockito.any(Checkout.class));
    }
}
