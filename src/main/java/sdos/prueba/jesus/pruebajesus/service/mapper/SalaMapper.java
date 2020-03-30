package sdos.prueba.jesus.pruebajesus.service.mapper;

import org.springframework.stereotype.Component;
import sdos.prueba.jesus.pruebajesus.domain.RecursoTecnico;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;

import java.util.stream.Collectors;

@Component
public class SalaMapper {

    public SalaDTO from(Sala sala) {

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setCodigo(sala.getCodigo());
        salaDTO.setNombre(sala.getNombre());
        salaDTO.setCapacidad(sala.getCapacidad());
        salaDTO.setRecursosTecnicos(sala.getRecursosTecnicos().stream().map(RecursoTecnico::getNombre).collect(Collectors.toSet()));
        return salaDTO;
    }
}
