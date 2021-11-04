package com.robinfood.techtest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robinfood.techtest.common.EncuestaEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="pregunta")
@NamedQuery(name="Pregunta.findAll", query="SELECT p FROM Pregunta p")
@Data
@NoArgsConstructor
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false)
    private Integer tipo;

    @ManyToOne
    @JoinColumn(name="encuesta_id")
    private Encuesta encuesta;

    @OneToMany(targetEntity=OpcionPregunta.class,
            mappedBy="pregunta",
            cascade=CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<OpcionPregunta> opciones;

}
