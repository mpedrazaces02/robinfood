package com.robinfood.techtest.repository;

import com.robinfood.techtest.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer>, JpaSpecificationExecutor<Pregunta> {
    List<Pregunta> findByEncuestaId(Integer encuesta_id);
}
