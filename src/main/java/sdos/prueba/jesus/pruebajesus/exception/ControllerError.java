package sdos.prueba.jesus.pruebajesus.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ControllerError {

    private String mensaje;
    private String informacion;
    private int codigo;

    public ControllerError(String mensaje, String informacion, int codigo) {

        this.mensaje = mensaje;
        this.informacion = informacion;
        this.codigo = codigo;
    }


}
