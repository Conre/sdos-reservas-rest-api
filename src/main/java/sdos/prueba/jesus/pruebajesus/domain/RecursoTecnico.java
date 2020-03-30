package sdos.prueba.jesus.pruebajesus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecursoTecnico{

    @Id
    @Column(length = 255)
    private String codigo;
    @Column(length = 255)
    private String nombre;

}
