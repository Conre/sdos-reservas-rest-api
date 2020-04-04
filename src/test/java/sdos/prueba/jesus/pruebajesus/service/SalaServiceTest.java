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
import sdos.prueba.jesus.pruebajesus.domain.RecursoTecnico;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.RecursoTecnicoDTO;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTORecursosNombreOnly;
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
        RecursoTecnicoDTO recurso = new RecursoTecnicoDTO();
        recurso.setCodigo("1234");
        recurso.setNombre("Televisor");
        Sala sala = new Sala();
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setCodigo("1234");
        salaDTO.setNombre("Sala 1");
        salaDTO.setCapacidad(2);
        salaDTO.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(recurso)));

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

        RecursoTecnicoDTO recurso = new RecursoTecnicoDTO();
        recurso.setCodigo("12");
        recurso.setNombre("Televisor");

        RecursoTecnicoDTO recurso2 = new RecursoTecnicoDTO();
        recurso.setCodigo("13");
        recurso.setNombre("Proyector");

        SalaDTO sala1 = new SalaDTO();
        sala1.setCodigo("12");
        sala1.setNombre("Sala 1");
        sala1.setCapacidad(3);
        sala1.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(recurso , recurso2)));

        RecursoTecnicoDTO recurso3 = new RecursoTecnicoDTO();
        recurso.setCodigo("14");
        recurso.setNombre("Pizarra digital");
        SalaDTO sala2 = new SalaDTO();
        sala2.setCodigo("13");
        sala2.setNombre("Sala 2");
        sala2.setCapacidad(5);
        sala2.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(recurso3)));

        List<SalaDTO> salasDTO = new ArrayList<>();
        salasDTO.add(sala1);
        salasDTO.add(sala2);

        when(salaRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(salas));
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

    @Test
    void givenSalaDTO_whenSaveSala_thenCallSalaMapper(){
        SalaDTO sala = new SalaDTO();

        when(salaRepository.save(any(Sala.class))).thenReturn(new Sala());
        when(salaMapper.to(any(SalaDTO.class))).thenReturn(new Sala());

        salaService.saveSala(sala);

        verify(salaMapper).to(sala);
    }

    @Test
    void givenSalaIdAndSalaDTORecursosNombreOnly_whenUpdateSalaById_thenFindSalaById() throws SalaNotFoundException {
        String idSala = "1234";
        SalaDTORecursosNombreOnly toUpdate = new SalaDTORecursosNombreOnly();
        toUpdate.setNombre("Sala 2");
        toUpdate.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnicoDTO("14","Pizarra digital"))));

        when(salaRepository.findById(anyString())).thenReturn(Optional.of(new Sala()));

        salaService.updateSalaById(idSala, toUpdate);


        verify(salaRepository).findById(idSala);
    }

    @Test
    void givenNonExistingSalaIdAndSalaDTORecursosNombreOnly_whenGetSalaId_thenReturnSalaNotFoundException() throws SalaNotFoundException{
        String idSala = "1234";
        SalaDTORecursosNombreOnly toUpdate = new SalaDTORecursosNombreOnly();
        toUpdate.setNombre("Sala 2");
        toUpdate.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnicoDTO("14","Pizarra digital"))));
        when(salaRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(SalaNotFoundException.class, ()->salaService.updateSalaById(idSala, toUpdate));
    }

    @Test
    void givenSalaIdAndSalaDTORecursosNombreOnly_whenUpdateSalaById_thenCallSalaMapperUpdate() throws SalaNotFoundException {
        String idSala = "1234";
        SalaDTORecursosNombreOnly toUpdate = new SalaDTORecursosNombreOnly();
        toUpdate.setNombre("Sala 2");
        toUpdate.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnicoDTO("14","Pizarra digital"))));

        Sala sala = new Sala();
        sala.setCodigo(idSala);
        sala.setNombre("Sala 1");
        sala.setCapacidad(2);
        sala.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"))));

        Sala sala2 = new Sala();
        sala2.setCodigo(idSala);
        sala2.setNombre(toUpdate.getNombre());
        sala2.setCapacidad(2);
        sala2.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnico("14","Pizarra digital"))));

        when(salaRepository.findById(anyString())).thenReturn(Optional.of(sala));
        when(salaMapper.updateNombreAndRecursos(any(Sala.class), any(SalaDTORecursosNombreOnly.class))).thenReturn(sala2);

        salaService.updateSalaById(idSala, toUpdate);


        verify(salaMapper, times(1)).updateNombreAndRecursos(sala,toUpdate);
    }


    @Test
    void givenSalaIdAndSalaDTORecursosNombreOnly_whenUpdateSalaById_thenPersisSala() throws SalaNotFoundException {
        String idSala = "1234";
        SalaDTORecursosNombreOnly toUpdate = new SalaDTORecursosNombreOnly();
        toUpdate.setNombre("Sala 2");
        toUpdate.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnicoDTO("14","Pizarra digital"))));

        Sala sala = new Sala();
        sala.setCodigo(idSala);
        sala.setNombre("Sala 1");
        sala.setCapacidad(2);
        sala.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"))));

        Sala sala2 = new Sala();
        sala2.setCodigo(idSala);
        sala2.setNombre(toUpdate.getNombre());
        sala2.setCapacidad(2);
        sala2.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnico("14","Pizarra digital"))));

        when(salaRepository.findById(anyString())).thenReturn(Optional.of(sala));
        when(salaMapper.updateNombreAndRecursos(any(Sala.class), any(SalaDTORecursosNombreOnly.class))).thenReturn(sala2);
        when(salaRepository.save(any(Sala.class))).thenReturn(new Sala());

        salaService.updateSalaById(idSala, toUpdate);


        verify(salaRepository).save(sala2);
    }
}
