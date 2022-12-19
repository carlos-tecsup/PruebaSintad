package com.sintad.prueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_tipo_contribuyente")
public class TipoContribuyente {
    @Id
    @Column(name = "id_tipo_contribuyente", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoContribuyente;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "estado", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean estado;

    @OneToOne(mappedBy = "tipoContribuyente")
    private Entidad entidad;
}
