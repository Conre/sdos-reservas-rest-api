package sdos.prueba.jesus.pruebajesus.web;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.SalaService;

import java.util.*;

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

    @Test
    void givenPageAndSize_whenGetSalas_thenReturnListSalaDTO() {
        SalaDTO sala1 = new SalaDTO();
        sala1.setCodigo("12");
        sala1.setNombre("Sala 1");
        sala1.setCapacidad(3);
        sala1.setRecursosTecnicos(Sets.newHashSet(Arrays.asList("Televisor" , "Proyector")));

        SalaDTO sala2 = new SalaDTO();
        sala2.setCodigo("13");
        sala2.setNombre("Sala 2");
        sala2.setCapacidad(5);
        sala2.setRecursosTecnicos(Sets.newHashSet(Arrays.asList("Pizarra digital")));

        List<SalaDTO> expected = new ArrayList<>();
        expected.add(sala1);
        expected.add(sala2);
        when(salaService.getSalas(anyInt(),anyInt())).thenReturn(expected);

        List<SalaDTO> salas = salaController.getSalas(anyInt(), anyInt());

        verify(salaService).getSalas(anyInt(), anyInt());

        assertEquals(salas, expected);
    }

    @Test
    void givenIdSala_whenDeleteSalaById_thenCallSalaService() throws SalaNotFoundException {
        String idSala = "1234";

        doNothing().when(salaService).deleteSalaById(anyString());

        salaController.deleteSalaById(idSala);

        verify(salaService).deleteSalaById(idSala);
    }
}
