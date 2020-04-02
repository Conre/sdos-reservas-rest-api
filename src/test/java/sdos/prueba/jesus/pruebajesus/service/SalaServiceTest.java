package sdos.prueba.jesus.pruebajesus.service;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import sdos.prueba.jesus.pruebajesus.dao.SalaRepository;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.mapper.SalaMapper;

import java.util.*;

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

    @Test
    void givenPageAndSize_whenGetSalas_thenCallSalaRepository() {
        List<Sala> salas = new ArrayList<>();
        int page = 1;
        int size = 10;
        when(salaRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<Sala>(salas));

        salaService.getSalas(page, size);

        verify(salaRepository).findAll(PageRequest.of(page,size));
    }

    @Test
    void givenPageAndSize_whenGetSalas_thenCallSalaMapper() {
        int page = 1;
        int size = 10;
        List<Sala> salas = new ArrayList<>();
        salas.add(new Sala());
        salas.add(new Sala());
        when(salaRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<Sala>(salas));
        when(salaMapper.from(any(Sala.class))).thenReturn(new SalaDTO()).thenReturn( new SalaDTO());
        salaService.getSalas(page,size);

        verify(salaMapper, times(2)).from(salas.get(0));

    }

    @Test
    void givenPageAndSize_whenGetSalas_thenReturnListSalaDTO() {
        int page = 1;
        int size = 10;
        Sala domain1 = new Sala();
        Sala domain2 = new Sala();
        List<Sala> salas = new ArrayList<>();
        salas.add(domain1);
        salas.add(domain2);

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

        List<SalaDTO> salasDTO = new ArrayList<>();
        salasDTO.add(sala1);
        salasDTO.add(sala2);

        when(salaRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<Sala>(salas));
        when(salaMapper.from(any(Sala.class))).thenReturn(sala1).thenReturn(sala2);


        List<SalaDTO> expected = salaService.getSalas( page, size);
        Assertions.assertEquals(salasDTO, expected);
    }

    @Test
    void givenSalaId_whenDeleteSalaById_thenCallSalaRepository() throws SalaNotFoundException {
        String idSala = "1234";
        doNothing().when(salaRepository).deleteById(anyString());

        salaService.deleteSalaById(idSala);

        verify(salaRepository).deleteById(idSala);

    }

}
