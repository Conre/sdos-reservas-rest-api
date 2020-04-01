package sdos.prueba.jesus.pruebajesus.service.mapper;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdos.prueba.jesus.pruebajesus.domain.RecursoTecnico;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;

import java.util.Arrays;
import java.util.Set;

class SalaMapperTest {

    private SalaMapper salaMapper;

    @BeforeEach
    void setUp() {
        this.salaMapper = new SalaMapper();
    }

    @Test
    void givenSala_whenFrom_thenReturnSalaDTO() {
        Sala sala = new Sala();
        sala.setCodigo("1234");
        sala.setNombre("Sala 1");
        sala.setCapacidad(3);
        sala.setRecursosTecnicos(Sets.newHashSet(Arrays.asList(new RecursoTecnico("1212", "Televisor"),new RecursoTecnico("4444","Proyector"))));

        SalaDTO salaDTO = this.salaMapper.from(sala);

        SalaDTO expected = new SalaDTO();
        expected.setCodigo("1234");
        expected.setNombre("Sala 1");
        expected.setCapacidad(3);
        expected.setRecursosTecnicos(Sets.newHashSet(Arrays.asList("Televisor", "Proyector")));

        Assertions.assertEquals(salaDTO, expected);
    }


}