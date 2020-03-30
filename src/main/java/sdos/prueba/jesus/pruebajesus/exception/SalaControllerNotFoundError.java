package sdos.prueba.jesus.pruebajesus.exception;

public class SalaControllerNotFoundError extends ControllerError{

    public SalaControllerNotFoundError(String idSala) {
        super("La sala indicada no se encuentra en el sistema", "identificador de sala: "+idSala, 404);
    }
}
