package com.robinfood.techtest.controller;

import com.robinfood.techtest.common.APIResponse;
import com.robinfood.techtest.dto.CreateEncuestaDTO;
import com.robinfood.techtest.dto.CreatePreguntaDTO;
import com.robinfood.techtest.dto.EncuestaDTO;
import com.robinfood.techtest.entity.Pregunta;
import com.robinfood.techtest.service.PreguntaService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "pregunta/v1")
@Api(value = "PreguntaController", tags = { "Pregunta Controller" })
public class PreguntaController {

    @Autowired
    PreguntaService preguntaService;

    @ApiOperation(value = "Registra una pergunta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Pregunta.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @PatchMapping(value = "/crear-pregunta", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createPregunta(
            @RequestBody final CreatePreguntaDTO createPreguntaDTO) {
        try {
            return ResponseEntity.ok(preguntaService.createPregunta(createPreguntaDTO));
        } catch (Exception e) {
            log.error("Error guardando las preguntas.", e);
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ApiOperation(value = "Obtiene las preguntas",
            authorizations = {@Authorization(value = "Bearer")}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Pregunta[].class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @GetMapping(value = "/{encuesta_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getPreguntasByEncuestaId(@ApiParam(required = true, name = "encuesta_id", value = "ID  de la encuesta.", type = "int")
                                             @PathVariable final Integer encuesta_id) {
        try {
            return ResponseEntity.ok(preguntaService.getPreguntaByEncuestaId(encuesta_id));
        } catch (Exception e) {
            log.error("Error consultando las preguntas.", e);
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

}
