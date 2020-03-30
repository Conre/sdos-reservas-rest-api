package sdos.prueba.jesus.pruebajesus.service;

import org.springframework.stereotype.Service;
import sdos.prueba.jesus.pruebajesus.dao.SalaRepository;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.mapper.SalaMapper;

import java.util.Optional;

@Service
public class SalaService {

    private SalaRepository salaRepository;
    private SalaMapper salaMapper;

    public SalaService(SalaRepository salaRepository, SalaMapper salaMapper) {

        this.salaRepository = salaRepository;
        this.salaMapper = salaMapper;
    }

    public SalaDTO getSalaById(String codigo) throws SalaNotFoundException {
        Optional<Sala> sala = salaRepository.findById(codigo);
        return salaMapper.from(sala.orElseThrow(()-> new SalaNotFoundException(codigo)));
    }
}
