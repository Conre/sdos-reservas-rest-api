package sdos.prueba.jesus.pruebajesus.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTORecursosNombreOnly;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.SalaService;

import java.util.List;

@RestController
public class SalaController {

    private SalaService salaService;

    public SalaController(SalaService salaService) {

        this.salaService = salaService;
    }

    @GetMapping( path = "/salas/{idSala}")
    public SalaDTO getSalaById(@PathVariable String idSala) throws SalaNotFoundException {
        return salaService.getSalaById(idSala);
    }

    @GetMapping( path = "/salas")
    public List<SalaDTO> getSalas(@RequestParam int page, @RequestParam int size) {
       return  salaService.getSalas(page, size);
    }

    @DeleteMapping( path = "/salas/{idSala}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalaById(@PathVariable String idSala) throws SalaNotFoundException {
        salaService.deleteSalaById(idSala);
    }

    @PostMapping(path = "salas/nueva-sala")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSala(SalaDTO sala) {
        salaService.saveSala(sala);
    }

    @PatchMapping(path = "/salas/{idSala}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSalaById(String idSala, SalaDTORecursosNombreOnly toUpdate) throws SalaNotFoundException {
        salaService.updateSalaById(idSala, toUpdate);
    }
}
