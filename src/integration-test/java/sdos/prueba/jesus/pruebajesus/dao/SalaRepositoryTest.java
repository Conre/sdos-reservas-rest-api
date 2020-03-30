package sdos.prueba.jesus.pruebajesus.dao;

import org.assertj.core.util.Sets;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sdos.prueba.jesus.pruebajesus.domain.RecursoTecnico;
import sdos.prueba.jesus.pruebajesus.domain.Sala;

import java.util.Arrays;

@DataJpaTest
public class SalaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SalaRepository salaRepository;

    @Test
    void givenIdSala_whenFindByIdSala_thenReturnSala() {
        Sala sala = new Sala("1234","Sala 1", 3, Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"), new RecursoTecnico("13","Proyector"))));
        this.entityManager.persist(new Sala("1234","Sala 1", 3, Sets.newHashSet(Arrays.asList(new RecursoTecnico("12","Televisor"), new RecursoTecnico("13","Proyector")))));
        Sala expected = this.salaRepository.findById("1234").get();

        assertEquals(sala, expected);
    }
}
