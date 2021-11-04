package com.robinfood.techtest.service;

import com.robinfood.techtest.dto.CreatePreguntaDTO;
import com.robinfood.techtest.dto.EncuestaDTO;
import com.robinfood.techtest.dto.PreguntaDTO;
import com.robinfood.techtest.entity.Encuesta;
import com.robinfood.techtest.entity.Pregunta;
import com.robinfood.techtest.repository.PreguntaRepository;
import com.robinfood.techtest.util.MappingService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PreguntaService {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private EncuestaService encuestaService;

    @Autowired
    private PreguntaRepository repository;

    public List<PreguntaDTO> getPreguntaByEncuestaId(Integer id_encuesta) throws NotFoundException {
        List<Pregunta> preguntas = repository.findByEncuestaId(id_encuesta);

        if(preguntas.size()==0){
            throw new NotFoundException("No se encontraron opciones asociadas al id pregunta suministrado");
        }

        return mappingService.mapList(preguntas,PreguntaDTO.class);
    }

    public PreguntaDTO getPreguntaById(Integer id_pregunta) throws NotFoundException {
        Optional<Pregunta> pregunta = repository.findById(id_pregunta);

        if(!pregunta.isPresent()){
            throw new NotFoundException("No se encontró ninguna pregunta con el id suministrado");
        }

        return mappingService.map(pregunta.get(),PreguntaDTO.class);
    }

    public PreguntaDTO createPregunta(CreatePreguntaDTO preguntaDTO) throws InvalidAttributesException, NotFoundException {

        EncuestaDTO encuesta = encuestaService.getEncuestaById(preguntaDTO.getEncuesta_id());

        if(!Optional.of(encuesta).isPresent()){
            throw new NotFoundException("No se encontró ninguna encuesta con el id suministrado");
        }

        Pregunta pregunta = mappingService.map(preguntaDTO,Pregunta.class);
        pregunta.setEncuesta(mappingService.map(encuesta,Encuesta.class));

        pregunta = this.repository.save(pregunta);

        return mappingService.map(pregunta,PreguntaDTO.class);

    }
}
