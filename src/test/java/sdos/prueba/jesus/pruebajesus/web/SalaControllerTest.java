package sdos.prueba.jesus.pruebajesus.web;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.SalaService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SalaControllerTest {

    private SalaController salaController;
    private SalaService salaService;

    @BeforeEach
    void setUp() {
        salaService = mock(SalaService.class);
        salaController = new SalaController(salaService);
    }

    @Test
    void givenIdSala_whenGetSalaById_thenReturnSala() throws SalaNotFoundException {
        String idSala = "1234";
        SalaDTO expected = new SalaDTO();
        expected.setCodigo("1234");
        expected.setNombre("Sala 1");
        expected.setCapacidad(2);
        expected.setRecursosTecnicos(Sets.newHashSet(Arrays.asList("Televisor")));
        when(salaService.getSalaById(anyString())).thenReturn(expected);

        SalaDTO sala = salaController.getSalaById(idSala);

        verify(salaService).getSalaById(idSala);
        assertEquals(sala, expected);
    }
}
