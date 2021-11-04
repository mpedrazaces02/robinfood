package com.robinfood.techtest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="encuesta")
@NamedQuery(name="Encuesta.findAll", query="SELECT e FROM Encuesta e")
@Data
@NoArgsConstructor
public class Encuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Integer id;

    @Column(name="titulo", nullable=false)
    private String titulo;

    @Column(name="fecha_inicio", nullable=false, updatable = false)
    private Date fecha_inicio;

    @Column(name="fecha_fin", nullable=false)
    private Date fecha_fin;

    @Column(nullable = false)
    private Integer estado;

    @OneToMany(targetEntity=Pregunta.class,
               mappedBy="encuesta",
               cascade=CascadeType.ALL,
               fetch = FetchType.EAGER)
    private List<Pregunta> preguntas;

}
