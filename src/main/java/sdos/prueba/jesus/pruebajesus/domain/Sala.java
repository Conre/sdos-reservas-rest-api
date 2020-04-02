package sdos.prueba.jesus.pruebajesus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sala {

    @Id
    private String codigo;
    @Column(length = 255)
    private String nombre;
    @Column
    private int capacidad;
    @ManyToMany(cascade = CascadeType.REFRESH)
    private Set<RecursoTecnico> recursosTecnicos;


}
