package com.robinfood.techtest.repository;

import com.robinfood.techtest.entity.OpcionPregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OpcionPreguntaRepository extends JpaRepository<OpcionPregunta, Integer>, JpaSpecificationExecutor<OpcionPregunta> {

    List<OpcionPregunta> findByPreguntaId(Integer pregunta_id);

}
