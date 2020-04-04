package sdos.prueba.jesus.pruebajesus.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sdos.prueba.jesus.pruebajesus.dao.SalaRepository;
import sdos.prueba.jesus.pruebajesus.domain.Sala;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTORecursosNombreOnly;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.mapper.SalaMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<SalaDTO> getSalas(int page, int size) {

        if(size > 20){
            size = 20;
        }
        return salaRepository.findAll(PageRequest.of(page,size)).getContent().stream().map(sala -> salaMapper.from(sala)).collect(Collectors.toList());
    }

    public void deleteSalaById(String idSala) throws SalaNotFoundException {
        salaRepository.deleteById(idSala);
    }

    public void saveSala(SalaDTO sala) {
        salaMapper.from(salaRepository.save(salaMapper.to(sala)));
    }

    public void updateSalaById(String idSala, SalaDTORecursosNombreOnly toUpdate) throws SalaNotFoundException {
        Sala sala = salaRepository.findById(idSala).orElseThrow(()->new SalaNotFoundException(idSala));
        Sala toSave = salaMapper.updateNombreAndRecursos(sala, toUpdate);
        salaRepository.save(toSave);
    }
}
