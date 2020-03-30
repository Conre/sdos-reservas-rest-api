package sdos.prueba.jesus.pruebajesus.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SalaDTO {

    private String codigo;
    private String nombre;
    private int capacidad;
    private Set<String> recursosTecnicos;


}
