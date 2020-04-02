package sdos.prueba.jesus.pruebajesus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class SalaDTO {

    private String codigo;
    private String nombre;
    private int capacidad;
    private Set<String> recursosTecnicos;


}
