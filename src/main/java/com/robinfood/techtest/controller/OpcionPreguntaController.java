package com.robinfood.techtest.controller;

import com.robinfood.techtest.common.APIResponse;
import com.robinfood.techtest.dto.CreateOpcionPreguntaDTO;
import com.robinfood.techtest.entity.Pregunta;
import com.robinfood.techtest.service.OpcionPreguntaService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "opcion-pregunta/v1")
@Api(value = "OpcionPreguntaController", tags = { "Opcion Pregunta Controller" })
public class OpcionPreguntaController {

    @Autowired
    OpcionPreguntaService opcionPreguntaService;

    @ApiOperation(value = "Asocia una opcion a una pregunta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Pregunta.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @PatchMapping(value = "/asociar-opcion-pregunta", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createOpcionPregunta(
            @RequestBody final CreateOpcionPreguntaDTO createOpcionPreguntaDTO) {
        try {
            return ResponseEntity.ok(opcionPreguntaService.createOpcionPregunta(createOpcionPreguntaDTO));
        } catch (Exception e) {
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ApiOperation(value = "Obtiene las opciones asociadas a las preguntas",
            authorizations = {@Authorization(value = "Bearer")}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Pregunta[].class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @GetMapping(value = "/{pregunta_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOpcionByPreguntaId(@ApiParam(required = true, name = "pregunta_id", value = "ID  de la encuesta.", type = "int")
                                                      @PathVariable final Integer pregunta_id) {
        try {
            return ResponseEntity.ok(opcionPreguntaService.getOpcionesByPreguntaId(pregunta_id));
        } catch (Exception e) {
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}
