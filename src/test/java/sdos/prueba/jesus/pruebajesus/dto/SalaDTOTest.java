package sdos.prueba.jesus.pruebajesus.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import sdos.prueba.jesus.pruebajesus.config.JacksonConfig;

import java.io.IOException;
import java.util.Arrays;

public class SalaDTOTest {

    private SalaDTO salaDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new JacksonConfig().objectMapper();
        salaDTO = new SalaDTO();
        salaDTO.setCodigo("1234");
        salaDTO.setNombre("Sala 1");
        salaDTO.setCapacidad(3);
        salaDTO.setRecursosTecnicos(Sets.newHashSet(Arrays.asList( new RecursoTecnicoDTO("12","Televisor"),
                new RecursoTecnicoDTO("13","Proyector"),
                new RecursoTecnicoDTO("14","Pizarra digital"),
                new RecursoTecnicoDTO("15","Videoconferencia"))));
    }

    @Test
    void serialize() throws IOException {
        String fixture = objectMapper.writeValueAsString(objectMapper.readValue(ResourceUtils.getFile("classpath:fixtures/sala-dto.json"), SalaDTO.class));
        String serialized = objectMapper.writeValueAsString(salaDTO);

        Assertions.assertEquals(fixture, serialized);
    }

    @Test
    void deserialize() throws IOException {
        SalaDTO fixture = objectMapper.readValue(ResourceUtils.getFile("classpath:fixtures/sala-dto.json"), SalaDTO.class);

        Assertions.assertEquals(fixture, salaDTO);
    }
}
