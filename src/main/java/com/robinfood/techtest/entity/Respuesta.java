package com.robinfood.techtest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="respuesta")
@NamedQuery(name="Respuesta.findAll", query="SELECT r FROM Respuesta r")
@Data
@NoArgsConstructor
public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="pregunta_id")
    private Pregunta pregunta;

    private String respuesta;

}
