package com.robinfood.techtest.controller;

import com.robinfood.techtest.common.APIResponse;
import com.robinfood.techtest.dto.CreateEncuestaCompletaDTO;
import com.robinfood.techtest.dto.CreateEncuestaDTO;
import com.robinfood.techtest.dto.EncuestaDTO;
import com.robinfood.techtest.service.EncuestaService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "encuesta/v1")
@Api(value = "EncuestaController", tags = { "Encuesta Controller" })
public class EncuestaController {

    @Autowired
    private EncuestaService encuestaService;

    @ApiOperation(value = "Registra una encuesta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EncuestaDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @PatchMapping(value = "/crear-encuesta", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createEncuesta(
            @RequestBody final CreateEncuestaDTO encuestaDTO) {
        try {
            return ResponseEntity.ok(encuestaService.createEncuesta(encuestaDTO));
        } catch (Exception e) {
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ApiOperation(value = "Obtiene la informacion de todas las encuestas.",
            authorizations = {@Authorization(value = "Bearer")}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EncuestaDTO[].class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @GetMapping(value = "/consultar-todas", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getEncuestas() {
        try {
            return ResponseEntity.ok(encuestaService.getAllEncuestas());
        } catch (Exception e) {
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ApiOperation(value = "Obtiene la informacion de una encuesta por id",
            authorizations = {@Authorization(value = "Bearer")}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EncuestaDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getEncuestaById(@ApiParam(required = true, name = "id", value = "ID  de la encuesta.", type = "int")
                                                 @PathVariable final Integer id) {
        try {
            return ResponseEntity.ok(encuestaService.getEncuestaById(id));
        } catch (Exception e) {
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ApiOperation(value = "Registra una encuesta con preguntas y opciones.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EncuestaDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Error", response = APIResponse.class)})
    @PatchMapping(value = "/crear-encuesta-completa", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createEncuestaCompleta(
            @RequestBody final CreateEncuestaCompletaDTO encuestaDTO) {
        try {
            return ResponseEntity.ok(encuestaService.createEncuestaCompleta(encuestaDTO));
        } catch (Exception e) {
            APIResponse response = new APIResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
