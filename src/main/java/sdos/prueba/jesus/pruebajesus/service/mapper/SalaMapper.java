package sdos.prueba.jesus.pruebajesus.service.mapper;

import org.springframework.stereotype.Component;
import sdos.prueba.jesus.pruebajesus.domain.RecursoTecnico;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.RecursoTecnicoDTO;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTORecursosNombreOnly;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SalaMapper {

    public SalaDTO from(Sala sala) {

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setCodigo(sala.getCodigo());
        salaDTO.setNombre(sala.getNombre());
        salaDTO.setCapacidad(sala.getCapacidad());
        salaDTO.setRecursosTecnicos(sala.getRecursosTecnicos().stream().map(r -> new RecursoTecnicoDTO(r.getCodigo(), r.getNombre())).collect(Collectors.toSet()));
        return salaDTO;
    }

    public Sala to(SalaDTO salaDTO) {
        Sala sala = new Sala();
        sala.setCodigo(salaDTO.getCodigo());
        sala.setNombre(salaDTO.getNombre());
        sala.setCapacidad(salaDTO.getCapacidad());
        sala.setRecursosTecnicos(salaDTO.getRecursosTecnicos().stream().map( r -> new RecursoTecnico(r.getCodigo(), r.getNombre())).collect(Collectors.toSet()));
        return sala;
    }

    public Sala updateNombreAndRecursos(Sala sala, SalaDTORecursosNombreOnly update){
        sala.setNombre(update.getNombre());
        sala.setRecursosTecnicos(update.getRecursosTecnicos().stream().map( r -> new RecursoTecnico(r.getCodigo(), r.getNombre())).collect(Collectors.toSet()));
        return sala;
    }
}
