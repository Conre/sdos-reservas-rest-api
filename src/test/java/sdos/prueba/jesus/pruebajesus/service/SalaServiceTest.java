package sdos.prueba.jesus.pruebajesus.service;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdos.prueba.jesus.pruebajesus.dao.SalaRepository;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.mapper.SalaMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class SalaServiceTest {

    private SalaService salaService;
    private SalaRepository salaRepository;
    private SalaMapper salaMapper;

    @BeforeEach
    void setUp() {
        this.salaRepository = mock(SalaRepository.class);
        this.salaMapper = mock(SalaMapper.class);
        this.salaService = new SalaService(salaRepository, salaMapper);
    }

    @Test
    void givenSalaId_whenGetSalaById_thenCallSalaRepository() throws SalaNotFoundException {
       String idSala = "1234";
       when(salaRepository.findById(anyString())).thenReturn(Optional.of(new Sala()));

       salaService.getSalaById(idSala);

       verify(salaRepository).findById(idSala);

    }

    @Test
    void givenSalaId_whenGetSalaById_thenCallSalaMapper() throws SalaNotFoundException {
        String idSala = "1234";
        Sala sala = new Sala();

        when(salaRepository.findById(anyString())).thenReturn(Optional.of(new Sala()));
        when(salaMapper.from(any(Sala.class))).thenReturn(new SalaDTO());
        salaService.getSalaById(idSala);

        verify(salaMapper).from(sala);
    }

    @Test
    void givenSalaId_whenGetSalaById_thenReturnSalaDTO() throws SalaNotFoundException {
        String idSala = "1234";
        Sala sala = new Sala();
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setCodigo("1234");
        salaDTO.setNombre("Sala 1");
        salaDTO.setCapacidad(2);
        salaDTO.setRecursosTecnicos(Sets.newHashSet(Arrays.asList("Televisor")));

        when(salaRepository.findById(anyString())).thenReturn(Optional.of(sala));
        when(salaMapper.from(any(Sala.class))).thenReturn(salaDTO);

        SalaDTO expected = salaService.getSalaById(idSala);
        Assertions.assertEquals(salaDTO, expected);

    }

    @Test
    void givenNonExistingSalaId_whenGetSalaId_thenReturnSalaNotFoundException() {
        String idSala = "1234";
        when(salaRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(SalaNotFoundException.class, ()->salaService.getSalaById("1"));

        verify(salaMapper, times(0)).from(any(Sala.class));
    }
}
