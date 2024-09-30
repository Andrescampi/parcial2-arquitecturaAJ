package co.edu.unisabana.parcial.ServicesTest;

import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.service.CheckpointDAO;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDAO {

    @Mock
    private CheckpointRepository checkpointRepository;

    @InjectMocks
    private CheckpointDAO checkpointDAO;

    @Test
    void guardarCheckin() {
        Checkin checkin = new Checkin("Facility1", "Driver1", 15);
        Checkpoint checkpointEntity = Checkpoint.fromCheckin(checkin);

        checkpointDAO.saveCheckin(checkin);

        verify(checkpointRepository, times(1)).save(checkpointEntity);
    }


    @Test
    void guardarCheckout() {
        Checkout checkout = new Checkout("Facility1", "Driver1", 15);
        Checkpoint checkpointEntity = Checkpoint.fromCheckout(checkout);

        checkpointDAO.saveCheckout(checkout);

        verify(checkpointRepository, times(1)).save(checkpointEntity);
    }

    @Test
    void encontrarUltimoCheckinExistente(){
        Checkpoint checkpointEntity = new Checkpoint();
        checkpointEntity.setDriver("Driver1");
        checkpointEntity.setFacility("Facility1");
        checkpointEntity.setId(1);
        when(checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse("Driver1", "Facility1"))
                .thenReturn(Optional.of(checkpointEntity));

        Checkin result = checkpointDAO.findLastCheckin("Driver1", "Facility1");

        assertNotNull(result);
        verify(checkpointRepository, times(1))
                .findFirstByDriverAndFacilityAndFinalizedIsFalse("Driver1", "Facility1");
    }

    @Test
    void encontrarUltimoCheckinNoExistente(){
        when(checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse("Driver1", "Facility1"))
                .thenReturn(Optional.empty());

        Checkin result = checkpointDAO.findLastCheckin("Driver1", "Facility1");

        assertNull(result);
        verify(checkpointRepository, times(1))
                .findFirstByDriverAndFacilityAndFinalizedIsFalse("Driver1", "Facility1");

    }

    @Test
    void finalizarCheckin(){
        Checkin checkin = new Checkin("Facility1", "Driver1", 15);
        checkin.setId(1);
        Checkpoint checkpointEntity = new Checkpoint();
        checkpointEntity.setId(1);
        when(checkpointRepository.findById(1)).thenReturn(Optional.of(checkpointEntity));

        checkpointDAO.finishCheckin(checkin);

        assertTrue(checkpointEntity.isFinalized());
        verify(checkpointRepository, times(1)).findById(1);
        verify(checkpointRepository, times(1)).save(checkpointEntity);
    }
}
