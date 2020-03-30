package sdos.prueba.jesus.pruebajesus.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import sdos.prueba.jesus.pruebajesus.config.JacksonConfig;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.exception.SalaControllerAdvice;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.SalaService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SalaControllerITest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private SalaService salaService;

    private SalaControllerAdvice salaAdvice;

    @BeforeEach
    void setUp() {
        this.objectMapper = new JacksonConfig().objectMapper();
        this.salaService = mock(SalaService.class);
        this.salaAdvice = new SalaControllerAdvice();
        this.mockMvc = MockMvcBuilders.standaloneSetup(new SalaController(salaService)).setControllerAdvice(salaAdvice).build();
    }

    @Test
    void givenIdSala_whenGetSalaById_thenReturnReponse() throws Exception {
        SalaDTO sala = objectMapper.readValue(ResourceUtils.getFile("classpath:fixtures/sala-dto.json"), SalaDTO.class);
        when(salaService.getSalaById(anyString())).thenReturn(sala);
        this.mockMvc.perform(
                get("/sala/1234"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(sala)));

        verify(salaService).getSalaById("1234");
    }

    @Test
    void givenNonExistingSala_whenGetSalaById_thenReturnSalaNotFoundError() throws Exception {
        when(salaService.getSalaById(anyString())).thenThrow(new SalaNotFoundException("123"));

        this.mockMvc.perform(
                get("/sala/123"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

        verify(salaService).getSalaById("123");
    }
}