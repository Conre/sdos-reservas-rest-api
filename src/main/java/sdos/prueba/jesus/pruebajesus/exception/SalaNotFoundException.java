package sdos.prueba.jesus.pruebajesus.exception;

import lombok.Data;

@Data
public class SalaNotFoundException extends Exception{

    private String idSala;

    public SalaNotFoundException(String idSala) {
        super(idSala);
        this.idSala = idSala;
    }
}
