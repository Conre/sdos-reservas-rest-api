package sdos.prueba.jesus.pruebajesus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import sdos.prueba.jesus.pruebajesus.domain.RecursoTecnico;

import java.util.Set;

@Data
@NoArgsConstructor
public class SalaDTO {

    private String codigo;
    private String nombre;
    private int capacidad;
    private Set<RecursoTecnicoDTO> recursosTecnicos;


}
