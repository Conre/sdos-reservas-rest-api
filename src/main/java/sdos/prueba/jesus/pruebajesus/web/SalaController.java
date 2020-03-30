package sdos.prueba.jesus.pruebajesus.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sdos.prueba.jesus.pruebajesus.dto.SalaDTO;
import sdos.prueba.jesus.pruebajesus.exception.SalaNotFoundException;
import sdos.prueba.jesus.pruebajesus.service.SalaService;

@RestController
public class SalaController {

    private SalaService salaService;

    public SalaController(SalaService salaService) {

        this.salaService = salaService;
    }

    @GetMapping( path = "/sala/{idSala}")
    public SalaDTO getSalaById(@PathVariable String idSala) throws SalaNotFoundException {
        return salaService.getSalaById(idSala);
    }
}
