package com.robinfood.techtest.service;

import com.robinfood.techtest.dto.*;
import com.robinfood.techtest.entity.Encuesta;
import com.robinfood.techtest.entity.Pregunta;
import com.robinfood.techtest.repository.EncuestaRepository;
import com.robinfood.techtest.util.MappingService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
public class EncuestaService {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private EncuestaRepository repository;

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private OpcionPreguntaService opcionPreguntaService;

    /**
     * Devuelve el listado de las encuestas almacenadas
     * @return Una lista de encuestas
     */
    public List<EncuestaDTO> getAllEncuestas() throws NotFoundException {
        List<EncuestaDTO> encuestas =  mappingService.mapList(repository.findAll(), EncuestaDTO.class);

        if(encuestas.size() == 0){
           throw new NotFoundException("No hay encuestas registradas.");
        }

        return encuestas;
    }

    public EncuestaDTO getEncuestaById(Integer id_encuesta) throws NotFoundException {
        Optional<Encuesta> encuesta = repository.findById(id_encuesta);

        if(!encuesta.isPresent()){
            throw new NotFoundException("No se encontró encuesta con el id suministrado");
        }

        return mappingService.map(encuesta.get(),EncuestaDTO.class);
    }

    public EncuestaDTO createEncuesta(CreateEncuestaDTO encuestaDTO) throws InvalidAttributesException {
        if(encuestaDTO.getFecha_fin().after(encuestaDTO.getFecha_inicio())){
            Encuesta encuesta = repository.save(mappingService.map(encuestaDTO, Encuesta.class));
            return mappingService.map(encuesta, EncuestaDTO.class);
        }else{
           throw new InvalidAttributesException("La fecha fin debe ser mayor que la fecha de inicio");
        }
    }

    public EncuestaDTO createEncuestaCompleta(CreateEncuestaCompletaDTO encuestaDTO) throws InvalidAttributesException, NotFoundException {
        Encuesta encuesta;
        if(encuestaDTO.getPreguntas().size() > 0){
            if(encuestaDTO.getFecha_fin().after(encuestaDTO.getFecha_inicio())){

                encuesta = new Encuesta();
                encuesta.setTitulo(encuestaDTO.getTitulo());
                encuesta.setFecha_inicio(encuestaDTO.getFecha_inicio());
                encuesta.setFecha_fin(encuestaDTO.getFecha_fin());
                encuesta.setEstado(encuestaDTO.getEstado());

                encuesta = repository.save(encuesta);

                for(CreatePreguntaCompletaDTO createPreguntaCompletaDTO : encuestaDTO.getPreguntas()){

                    CreatePreguntaDTO createPreguntaDTO = new CreatePreguntaDTO();
                    createPreguntaDTO.setTitulo(createPreguntaCompletaDTO.getTitulo());
                    createPreguntaDTO.setTipo(createPreguntaCompletaDTO.getTipo());
                    createPreguntaDTO.setEncuesta_id(encuesta.getId());

                    PreguntaDTO preguntaSaved = preguntaService.createPregunta(createPreguntaDTO);

                    if(createPreguntaCompletaDTO.getOpciones() != null && createPreguntaCompletaDTO.getOpciones().size() > 0){
                        for(CreateOpcionPreguntaCompletaDTO createOpcionPreguntaCompletaDTO : createPreguntaCompletaDTO.getOpciones()){
                            CreateOpcionPreguntaDTO createOpcionPreguntaDTO = mappingService.map(createOpcionPreguntaCompletaDTO,CreateOpcionPreguntaDTO.class);
                            createOpcionPreguntaDTO.setPregunta_id(preguntaSaved.getId());
                            opcionPreguntaService.createOpcionPregunta(createOpcionPreguntaDTO);
                        }
                    }
                }
            }else{
                throw new InvalidAttributesException("La fecha fin debe ser mayor que la fecha de inicio");
            }
        }else{
            throw new InvalidAttributesException("La encuesta debe tener preguntas");
        }

        return this.getEncuestaById(encuesta.getId());
    }

}
