package com.robinfood.techtest.service;

import com.robinfood.techtest.dto.*;
import com.robinfood.techtest.entity.Encuesta;
import com.robinfood.techtest.entity.OpcionPregunta;
import com.robinfood.techtest.entity.Pregunta;
import com.robinfood.techtest.repository.OpcionPreguntaRepository;
import com.robinfood.techtest.util.MappingService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;

@Service
@Slf4j
public class OpcionPreguntaService {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private OpcionPreguntaRepository repository;

    public List<OpcionPreguntaDTO> getOpcionesByPreguntaId(Integer id_pregunta) throws NotFoundException {

        List<OpcionPregunta> opcionPreguntas = repository.findByPreguntaId(id_pregunta);

        if(opcionPreguntas.size()==0){
            throw new NotFoundException("No se encontraron preguntas asociadas al id suministrado");
        }

        return mappingService.mapList(opcionPreguntas,OpcionPreguntaDTO.class);
    }

    public OpcionPreguntaDTO createOpcionPregunta(CreateOpcionPreguntaDTO createOpcionPreguntaDTO) throws InvalidAttributesException, NotFoundException {

        PreguntaDTO preguntaDTO = preguntaService.getPreguntaById(createOpcionPreguntaDTO.getPregunta_id());

        OpcionPregunta opcionPregunta = mappingService.map(createOpcionPreguntaDTO,OpcionPregunta.class);
        opcionPregunta.setPregunta(mappingService.map(preguntaDTO, Pregunta.class));

        opcionPregunta = this.repository.save(opcionPregunta);

        return mappingService.map(opcionPregunta,OpcionPreguntaDTO.class);

    }
}
