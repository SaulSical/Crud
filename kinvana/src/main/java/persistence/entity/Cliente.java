package persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    private Integer codigoCLiente;
    @Column
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String genero;
    private Integer edad;


}
