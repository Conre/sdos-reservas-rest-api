package sdos.prueba.jesus.pruebajesus.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import sdos.prueba.jesus.pruebajesus.config.JacksonConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.mockito.Mockito.mock;

public class SalaControllerAdviceTest {

    private SalaControllerAdvice salaAdvice;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        salaAdvice = new SalaControllerAdvice();
        objectMapper = new JacksonConfig().objectMapper();
    }

    @Test
    void givenSalaNotFoundException_whenHandleNotFoundException_thenReturnControllerError() throws IOException {
        SalaNotFoundException exception = new SalaNotFoundException("123");
        ControllerError expected = objectMapper.readValue(ResourceUtils.getFile("classpath:fixtures/error/sala-not-found-error.json"), ControllerError.class);
        ControllerError controllerError = salaAdvice.handleNotFoundException(mock(HttpServletRequest.class), exception);

        Assertions.assertEquals(controllerError, expected);
    }
}
