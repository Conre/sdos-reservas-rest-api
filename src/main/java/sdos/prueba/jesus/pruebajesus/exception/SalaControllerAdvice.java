package sdos.prueba.jesus.pruebajesus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sdos.prueba.jesus.pruebajesus.web.SalaController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackageClasses = SalaController.class)
public class SalaControllerAdvice {

    @ExceptionHandler(SalaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ControllerError handleNotFoundException(HttpServletRequest request, SalaNotFoundException exception){

        return new SalaControllerNotFoundError(exception.getIdSala());
    }
}
