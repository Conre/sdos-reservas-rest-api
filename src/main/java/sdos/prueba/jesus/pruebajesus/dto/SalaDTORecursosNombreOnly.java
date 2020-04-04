package sdos.prueba.jesus.pruebajesus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
public class SalaDTORecursosNombreOnly {

    private String nombre;
    private HashSet<RecursoTecnicoDTO> recursosTecnicos;



}
