package com.robinfood.techtest.repository;

import com.robinfood.techtest.entity.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EncuestaRepository extends JpaRepository<Encuesta, Integer>, JpaSpecificationExecutor<Encuesta> {

}
