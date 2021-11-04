package com.robinfood.techtest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="opcion_pregunta")
@NamedQuery(name="OpcionPregunta.findAll", query="SELECT op FROM OpcionPregunta op")
@Data
@NoArgsConstructor
public class OpcionPregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String texto;

    @Column(nullable = false, length = 50)
    private String ejemplo;

    @ManyToOne
    @JoinColumn(name="pregunta_id")
    private Pregunta pregunta;

}
