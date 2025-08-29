package org.datacoins.kinvana.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Clientes") // 👈 Debe coincidir con la tabla real
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigocliente") // 👈 nombre EXACTO de la columna en BD
    private Integer codigoCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "genero")
    private String genero;

    @Column(name = "edad")
    private Integer edad;
}
