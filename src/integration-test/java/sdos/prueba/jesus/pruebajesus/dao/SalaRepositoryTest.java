package sdos.prueba.jesus.pruebajesus.dao;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import sdos.prueba.jesus.pruebajesus.domain.RecursoTecnico;
import sdos.prueba.jesus.pruebajesus.domain.Sala;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class SalaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SalaRepository salaRepository;

    @Test
    void givenIdSala_whenFindByIdSala_thenReturnSala() {
        Sala sala = new Sala("1234","Sala 1", 3, Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"), new RecursoTecnico("13","Proyector"))));
        entityManager.persist(new Sala("1234","Sala 1", 3, Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"), new RecursoTecnico("13","Proyector")))));

        Sala expected = this.salaRepository.findById("1234").get();

        assertEquals(sala, expected);
    }

    @Test
    void givenPageRequest_whenFindAll_thenReturnPageSala() {
        RecursoTecnico recurso1 = new RecursoTecnico("124","Televisor");
        RecursoTecnico recurso2 = new RecursoTecnico("134","Proyector");
        entityManager.persist(recurso1);
        entityManager.persist(recurso2);
        entityManager.persist(new Sala("12345","Sala 1", 3, Sets.newHashSet(Arrays.asList(recurso1, recurso2))));
        entityManager.persist(new Sala("123456","Sala 2", 5, Sets.newHashSet(Arrays.asList(recurso1))));
        //this.entityManager.persist(new Sala("12345", "Sala 2", 5, Sets.newHashSet(Arrays.asList(new RecursoTecnico("14", "Televisor"), new RecursoTecnico("15", "Proyector")))));

        List<Sala> expected = new ArrayList<>();
        expected.add(new Sala("12345", "Sala 1", 3, Sets.newHashSet(Arrays.asList(new RecursoTecnico("124", "Televisor"), new RecursoTecnico("134", "Proyector")))));
        expected.add(new Sala("123456", "Sala 2", 5, Sets.newHashSet(Arrays.asList(new RecursoTecnico("124", "Televisor")))));

        List<Sala> paginacion =  this.salaRepository.findAll(PageRequest.of(0,20)).getContent();

        assertEquals(paginacion, expected);

    }

    @Test
    void givenIdSala_whenDeleteByIdSala_thenReturnNothing() {
        Sala sala = new Sala("1234","Sala 1", 3, Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"), new RecursoTecnico("13","Proyector"))));
        entityManager.persist(new Sala("1234","Sala 1", 3, Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"), new RecursoTecnico("13","Proyector")))));

        salaRepository.deleteById(sala.getCodigo());
        Optional<Sala> expected = this.salaRepository.findById("1234");

        assertFalse(expected.isPresent());
    }

}
